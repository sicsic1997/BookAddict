package com.book.addict.dao.impl;

import com.book.addict.dao.BookDAO;
import com.book.addict.dao.BookingDAO;
import com.book.addict.dto.BookDTO;
import com.book.addict.dto.BookingItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBookingDAO implements BookingDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private BookDAO bookDAO;

    @Value("${bookings.expiring.interval}")
    private int expiringInterval;

    @Transactional
    @Override
    public void saveBooking(int userId, List<BookingItemDTO> bookingItemDTOList, String description) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date date = new Date();
        String bookingId = dateFormat.format(date) + "_" + userId;
        BigDecimal totalPrice = BigDecimal.valueOf(0L);

        for (BookingItemDTO item:bookingItemDTOList) {
            String sqlInsert = "" +
                    "INSERT INTO BA_BOOK_TO_BOOKING_MAP VALUES ( " +
                    "   :idBooking, " +
                    "   :idBook, " +
                    "   :quantity" +
                    ") ";

            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("idBooking", bookingId);
            namedParameters.addValue("idBook", item.getBookId());
            namedParameters.addValue("quantity", item.getQuantity());
            jdbcTemplate.update(sqlInsert, namedParameters);

            Optional<BookDTO> bookDTOOptional = bookDAO.getBookById(item.getBookId());
            BookDTO bookDTO = bookDTOOptional.get();
            totalPrice = totalPrice.add(bookDTO.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        String sqlInsert = "INSERT INTO BA_BOOKINGS VALUES (" +
                "   :idBooking, " +
                "   NOW(), " +
                "   DATE_ADD(NOW(), INTERVAL :expiringInterval DAY)," +
                "   :deBooking, " +
                "   :idUser, " +
                "   :totalPrice, " +
                "   (SELECT ID_STATUS FROM BA_BOOKING_STATUS WHERE DE_STATUS = 'IN_PROGRESS') " +
                ")";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("idBooking", bookingId);
        namedParameters.addValue("deBooking", description);
        namedParameters.addValue("idUser", userId);
        namedParameters.addValue("totalPrice", totalPrice);
        namedParameters.addValue("expiringInterval", expiringInterval);
        jdbcTemplate.update(sqlInsert, namedParameters);

    }
}

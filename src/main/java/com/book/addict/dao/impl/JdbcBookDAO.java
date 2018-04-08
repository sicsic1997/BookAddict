package com.book.addict.dao.impl;

import com.book.addict.dao.BookDAO;
import com.book.addict.dto.BookDTO;
import com.book.addict.dto.PublisherDTO;
import com.book.addict.dto.WriterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBookDAO implements BookDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<BookDTO> getBookById(int idBook) {
        String sqlSelect = "" +
                "SELECT " +
                "    ID_BOOK, " +
                "    BOOK_NAME, " +
                "    DE_BOOK, " +
                "    PRICE, " +
                "    QUANTITY, " +
                "    ID_WRITER, " +
                "    ID_PUBLISHER, " +
                "    IMG_NAME " +
                "FROM BA_BOOKS " +
                "WHERE ID_BOOK = idBook;";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("idBook", idBook);

        BookDTO bookDTO = null;
        try {
            bookDTO = jdbcTemplate.queryForObject(sqlSelect, namedParameters, new PublisherDTOMapper());
        } catch (EmptyResultDataAccessException ignored) {

        }

        return Optional.ofNullable(bookDTO);
    }

    @Override
    public List<Integer> getAllBookIds() {
        String sqlSelect = "" +
                "SELECT " +
                "   ID_BOOK " +
                "FROM BA_BOOKS;";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        return jdbcTemplate.execute(sqlSelect, preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            List<Integer> results = new ArrayList<>();

            while(rs.next()) {
                Integer id = rs.getInt("ID_BOOK");
                results.add(id);
            }
            return results;
        });
    }


    class PublisherDTOMapper implements RowMapper<BookDTO> {
        @Override
        public BookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setIdBook(rs.getInt("ID_BOOK"));
            bookDTO.setBookName(rs.getString("BOOK_NAME"));
            bookDTO.setDeBook(rs.getString("DE_BOOK"));
            bookDTO.setPrice(rs.getBigDecimal("PRICE"));
            bookDTO.setQuantity(rs.getInt("QUANTITY"));
            bookDTO.setImgName(rs.getString("IMG_NAME"));

            WriterDTO writerDTO = new WriterDTO();
            writerDTO.setIdWriter(rs.getInt("ID_WRITER"));
            bookDTO.setWriter(writerDTO);

            PublisherDTO publisherDTO = new PublisherDTO();
            publisherDTO.setIdPublisher(rs.getInt("ID_PUBLISHER"));
            bookDTO.setPublisher(publisherDTO);

            return bookDTO;
        }
    }

}

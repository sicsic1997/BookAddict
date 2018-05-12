package com.book.addict.dao.impl;

import com.book.addict.constants.TextFilterType;
import com.book.addict.dao.BookDAO;
import com.book.addict.dao.CategoryDAO;
import com.book.addict.dao.WriterDAO;
import com.book.addict.domain.BookDashboardFilter;
import com.book.addict.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBookDAO implements BookDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    WriterDAO writerDAO;

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
                "WHERE ID_BOOK = :idBook;";

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

    @Override
    public BigDecimal getMaxBookPrice() {
        String sqlSelect = "" +
                "SELECT " +
                "   MAX(PRICE) MAX_PRICE " +
                "FROM BA_BOOKS; ";

        BigDecimal maxPrice = null;
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        try {
            maxPrice = jdbcTemplate.queryForObject(sqlSelect, namedParameters, new BigDecimalMapper());
        } catch (EmptyResultDataAccessException ignored) {

        }

        return maxPrice == null? BigDecimal.valueOf(0) : maxPrice;

    }

    @Override
    public List<Integer> getBookIdsByFilter(BookDashboardFilter filter) {
        String sqlSelect = "" +
                "SELECT DISTINCT " +
                "   books.ID_BOOK " +
                "FROM BA_BOOKS books " +
                "INNER JOIN BA_BOOK_TO_CATEGORIES_MAP map " +
                "   ON books.ID_BOOK = map.ID_BOOK " +
                "WHERE " +
                "    books.PRICE >= :minPrice AND " +
                "    books.PRICE <= :maxPrice AND " +
                "    map.ID_CATEGORY IN (:categories)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("minPrice", filter.getMinPrice());
        namedParameters.addValue("maxPrice", filter.getMaxPrice());

        List<Integer> categoriesIds = new ArrayList<>();
        if(filter.getCategoryDTOList().isEmpty()) {
            filter.setCategoryDTOList(categoryDAO.getAllCategories());
        }
        for (CategoryDTO category:filter.getCategoryDTOList()) {
            categoriesIds.add(category.getIdCategory());
        }
        namedParameters.addValue("categories", categoriesIds);

        return jdbcTemplate.execute(sqlSelect, namedParameters, preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            List<Integer> results = new ArrayList<>();

            while(rs.next()) {
                Integer id = rs.getInt("ID_BOOK");
                results.add(id);
            }
            return results;
        });
    }

    @Override
    public List<BookDashboardTextFilter> getAllBookDashboardFilter() {
        List<String> writersDeList = writerDAO.getAllWritersDe();
        List<String> bookDeList = getAllBooksName();
        List<BookDashboardTextFilter> results = new ArrayList<>();
        for (String writerDe:writersDeList) {
            BookDashboardTextFilter bookDashboardTextFilter = new BookDashboardTextFilter();
            bookDashboardTextFilter.setText(writerDe);
            bookDashboardTextFilter.setType(TextFilterType.AUTHOR);
            results.add(bookDashboardTextFilter);
        }
        for (String writerDe:bookDeList) {
            BookDashboardTextFilter bookDashboardTextFilter = new BookDashboardTextFilter();
            bookDashboardTextFilter.setText(writerDe);
            bookDashboardTextFilter.setType(TextFilterType.BOOK);
            results.add(bookDashboardTextFilter);
        }
        return results;
    }

    @Override
    public List<String> getAllBooksName() {
        String sqlSelect = "" +
                "SELECT " +
                "   books.BOOK_NAME " +
                "FROM BA_BOOKS books ";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        return jdbcTemplate.execute(sqlSelect, namedParameters, preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            List<String> results = new ArrayList<>();
            while(rs.next()) {
                String bookName = rs.getString("BOOK_NAME");
                results.add(bookName);
            }
            return results;
        });
    }

    class BigDecimalMapper implements RowMapper<BigDecimal> {
        @Override
        public BigDecimal mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getBigDecimal("MAX_PRICE");
        }
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

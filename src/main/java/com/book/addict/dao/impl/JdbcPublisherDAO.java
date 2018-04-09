package com.book.addict.dao.impl;

import com.book.addict.dao.PublisherDAO;
import com.book.addict.dto.PublisherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class JdbcPublisherDAO implements PublisherDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<PublisherDTO> getPublisherById(int idPublisher) {
        String sqlSelect = "" +
                "SELECT " +
                "    ID_PUBLISHER, " +
                "    DE_PUBLISHER, " +
                "    ADDRESS, " +
                "    EMAIL, " +
                "    PHONE_NUMBER " +
                "FROM BA_PUBLISHERS " +
                "WHERE ID_PUBLISHER = :idPublisher;";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("idPublisher", idPublisher);

        PublisherDTO publisherDTO = null;
        try {
            publisherDTO = jdbcTemplate.queryForObject(sqlSelect, namedParameters, new PublisherDTOMapper());
        } catch (EmptyResultDataAccessException ignored) {

        }

        return Optional.ofNullable(publisherDTO);

    }

    class PublisherDTOMapper implements RowMapper<PublisherDTO> {
        @Override
        public PublisherDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            PublisherDTO publihser = new PublisherDTO();
            publihser.setIdPublisher(rs.getInt("ID_PUBLISHER"));
            publihser.setDePublisher(rs.getString("DE_PUBLISHER"));
            publihser.setAddress(rs.getString("ADDRESS"));
            publihser.setEmail(rs.getString("EMAIL"));
            publihser.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            return publihser;
        }
    }

}

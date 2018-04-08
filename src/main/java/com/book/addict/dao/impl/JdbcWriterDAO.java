package com.book.addict.dao.impl;

import com.book.addict.dao.WriterDAO;
import com.book.addict.dto.WriterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcWriterDAO implements WriterDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<WriterDTO> getWriterById(int idWriter) {
        String sqlSelect = "" +
                "SELECT " +
                "    ID_WRITER, " +
                "    DE_WRITER " +
                "FROM BA_WRITERS " +
                "WHERE ID_WRITER = :idWriter;";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("idWriter", idWriter);

        WriterDTO writer = null;
        try {
            writer = jdbcTemplate.queryForObject(sqlSelect, namedParameters, new PublisherDTOMapper());
        } catch (EmptyResultDataAccessException ignored) {

        }

        return Optional.ofNullable(writer);
    }

    class PublisherDTOMapper implements RowMapper<WriterDTO> {
        @Override
        public WriterDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            WriterDTO writer = new WriterDTO();
            writer.setIdWriter(rs.getInt("ID_WRITER"));
            writer.setDeWriter(rs.getString("DE_WRITER"));
            return writer;
        }
    }

}

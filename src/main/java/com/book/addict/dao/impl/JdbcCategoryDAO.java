package com.book.addict.dao.impl;

import com.book.addict.dao.CategoryDAO;
import com.book.addict.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCategoryDAO implements CategoryDAO{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<CategoryDTO> getAllCategories() {
        String sqlSelect = "" +
                "SELECT " +
                "   ID_CATEGORY, " +
                "   DE_CATEGORY  " +
                "FROM BA_CATEGORIES";

        return jdbcTemplate.execute(sqlSelect, preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            List<CategoryDTO> results = new ArrayList<>();

            while(rs.next()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setIdCategory(rs.getInt("ID_CATEGORY"));
                categoryDTO.setDeCategory(rs.getString("DE_CATEGORY"));
                results.add(categoryDTO);
            }
            return results;
        });

    }

    @Override
    public List<CategoryDTO> getCategoriesByBookId(int idBook) {
        String sqlSelect = "" +
                "SELECT  " +
                "   categories.ID_CATEGORY,  " +
                "   categories.DE_CATEGORY  " +
                "FROM BA_CATEGORIES categories " +
                "INNER JOIN BA_BOOK_TO_CATEGORIES_MAP map " +
                "ON categories.ID_CATEGORY = map.ID_CATEGORY " +
                "WHERE map.ID_BOOK = :idBook ";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("idBook", idBook);


        return jdbcTemplate.execute(sqlSelect, namedParameters, preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            List<CategoryDTO> results = new ArrayList<>();

            while(rs.next()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setIdCategory(rs.getInt("ID_CATEGORY"));
                categoryDTO.setDeCategory(rs.getString("DE_CATEGORY"));
                results.add(categoryDTO);
            }
            return results;
        });
    }
}

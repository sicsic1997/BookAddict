package com.book.addict.dao;

import com.book.addict.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryDAO {

    List<CategoryDTO> getAllCategories();

    List<CategoryDTO> getCategoriesByBookId(int idBook);

}

package com.book.addict.service;

import com.book.addict.domain.BookDashboardFilter;
import com.book.addict.dto.BookDTO;
import com.book.addict.dto.CategoryDTO;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    List<BookDTO> getBooksByFilter(BookDashboardFilter bookDashboardFilter);

    BookDTO getBookById(int idBook);

    List<CategoryDTO> getAllCategories();

    BigDecimal getMaxBookPrice();

}

package com.book.addict.service.impl;

import com.book.addict.domain.BookDashboardFilter;
import com.book.addict.dto.BookDTO;
import com.book.addict.dto.CategoryDTO;
import com.book.addict.dto.WriterDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImplBookListMockGenerator {

    public List<BookDTO> getBooksForFilter() {
        List<BookDTO> bookDTOS = new ArrayList<>();

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        BookDTO bookDTO = new BookDTO();
        WriterDTO writerDTO = new WriterDTO();
        writerDTO.setIdWriter(1);
        writerDTO.setDeWriter("Graham Bell");
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setIdCategory(1);
        categoryDTO.setDescription("Action");
        categoryDTOS.add(categoryDTO);
        bookDTO.setWriter(writerDTO);
        bookDTO.setPrice(BigDecimal.valueOf(76));
        bookDTO.setCategories(categoryDTOS);
        bookDTO.setBookName("Happy Ending");
        bookDTOS.add(bookDTO);

        categoryDTOS = new ArrayList<>();
        bookDTO = new BookDTO();
        writerDTO = new WriterDTO();
        writerDTO.setIdWriter(2);
        writerDTO.setDeWriter("Ali Bell");
        categoryDTO = new CategoryDTO();
        categoryDTO.setIdCategory(2);
        categoryDTO.setDescription("Biography");
        categoryDTOS.add(categoryDTO);
        bookDTO.setWriter(writerDTO);
        bookDTO.setPrice(BigDecimal.valueOf(55));
        bookDTO.setCategories(categoryDTOS);
        bookDTO.setBookName("No love no glory");
        bookDTOS.add(bookDTO);

        return bookDTOS;
    }

    public BookDashboardFilter getBookDashboardFilterEmptyTextFilter() {
        BookDashboardFilter bookDashboardFilter = new BookDashboardFilter();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setIdCategory(1);
        categoryDTO.setDescription("Action");
        categoryDTOS.add(categoryDTO);
        categoryDTO = new CategoryDTO();
        categoryDTO.setIdCategory(2);
        categoryDTO.setDescription("Biography");
        categoryDTOS.add(categoryDTO);
        bookDashboardFilter.setCategoryDTOList(categoryDTOS);
        bookDashboardFilter.setMinPrice(BigDecimal.valueOf(50));
        bookDashboardFilter.setMaxPrice(BigDecimal.valueOf(100));
        bookDashboardFilter.setBookOrAuthorName("");
        return bookDashboardFilter;
    }

    public BookDashboardFilter getBookDashboardFilterNonEmptyTextFilter() {
        BookDashboardFilter bookDashboardFilter = new BookDashboardFilter();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setIdCategory(1);
        categoryDTO.setDescription("Action");
        categoryDTOS.add(categoryDTO);
        categoryDTO = new CategoryDTO();
        categoryDTO.setIdCategory(2);
        categoryDTO.setDescription("Biography");
        categoryDTOS.add(categoryDTO);
        bookDashboardFilter.setCategoryDTOList(categoryDTOS);
        bookDashboardFilter.setMinPrice(BigDecimal.valueOf(50));
        bookDashboardFilter.setMaxPrice(BigDecimal.valueOf(100));
        bookDashboardFilter.setBookOrAuthorName("gr");
        return bookDashboardFilter;
    }

}

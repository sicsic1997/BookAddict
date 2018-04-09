package com.book.addict.domain;

import com.book.addict.constants.BookDashboardFilterComparator;
import com.book.addict.dto.CategoryDTO;

import java.math.BigDecimal;
import java.util.List;

public class BookDashboardFilter {

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String bookOrAuthorName;
    private List<CategoryDTO> categoryDTOList;
    private BookDashboardFilterComparator field;

    public List<CategoryDTO> getCategoryDTOList() {
        return categoryDTOList;
    }

    public void setCategoryDTOList(List<CategoryDTO> categoryDTOList) {
        this.categoryDTOList = categoryDTOList;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getBookOrAuthorName() {
        return bookOrAuthorName;
    }

    public void setBookOrAuthorName(String bookOrAuthorName) {
        this.bookOrAuthorName = bookOrAuthorName;
    }

    public BookDashboardFilterComparator getField() {
        return field;
    }

    public void setField(BookDashboardFilterComparator field) {
        this.field = field;
    }
}

package com.book.addict.domain;

import com.book.addict.constants.FilterFieldToCompare;

import java.math.BigDecimal;

public class Filter {

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String bookOrAuthorName;
    private int pageNumber;
    private FilterFieldToCompare field;

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

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public FilterFieldToCompare getField() {
        return field;
    }

    public void setField(FilterFieldToCompare field) {
        this.field = field;
    }
}

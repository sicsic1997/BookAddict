package com.book.addict.dto;

import java.math.BigDecimal;
import java.util.List;

public class BookDTO {

    private int idBook;
    private String bookName;
    private String deBook;
    private BigDecimal price;
    private int quantity;
    private WriterDTO writer;
    private PublisherDTO publisher;
    private List<CategoryDTO> categories;
    private String imgName;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDeBook() {
        return deBook;
    }

    public void setDeBook(String deBook) {
        this.deBook = deBook;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public WriterDTO getWriter() {
        return writer;
    }

    public void setWriter(WriterDTO writer) {
        this.writer = writer;
    }

    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
        this.publisher = publisher;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}

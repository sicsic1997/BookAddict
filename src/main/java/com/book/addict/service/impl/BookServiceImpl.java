package com.book.addict.service.impl;

import com.book.addict.dao.BookDAO;
import com.book.addict.dao.CategoryDAO;
import com.book.addict.dao.PublisherDAO;
import com.book.addict.dao.WriterDAO;
import com.book.addict.domain.BookDashboardFilter;
import com.book.addict.dto.*;
import com.book.addict.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private WriterDAO writerDAO;

    @Autowired
    private PublisherDAO publisherDAO;


    @Override
    public List<BookDTO> getBooksByFilter(BookDashboardFilter bookDashboardFilter) {

        List<Integer> bookIds = bookDAO.getBookIdsByFilter(bookDashboardFilter);

        List<BookDTO> bookList = new ArrayList<>();
        for (Integer id:bookIds) {
            BookDTO book = getBookById(id);
            if(book != null) {
                bookList.add(book);
            }
        }

        bookList = getFilteredResults(bookList, bookDashboardFilter);

        Comparator<BookDTO> comparator = null;
        switch (bookDashboardFilter.getField()) {
            case ALPHABETICAL:
                comparator = Comparator.comparing(BookDTO::getBookName);
                break;
            case PRICE_LOW_TO_HIGH:
                comparator = Comparator.comparing(BookDTO::getPrice);
                break;
            case PRICE_HIGH_TO_LOW:
                comparator = Comparator.comparing(BookDTO::getPrice).reversed();
                break;
        }
        bookList = bookList.stream().sorted(comparator).collect(Collectors.toList());

        return bookList;

    }

    public List<BookDTO> getFilteredResults(List<BookDTO> bookList, BookDashboardFilter bookDashboardFilter) {

        if(bookDashboardFilter.getBookOrAuthorName() != null && !Objects.equals(bookDashboardFilter.getBookOrAuthorName(), "")) {
            bookList = bookList
                    .stream()
                    .filter(book -> book.getWriter().getDeWriter().toLowerCase().contains(bookDashboardFilter.getBookOrAuthorName().toLowerCase()) ||
                            book.getBookName().toLowerCase().contains(bookDashboardFilter.getBookOrAuthorName().toLowerCase()))
                    .collect(Collectors.toList());
        }

        return bookList;

    }

    @Override
    public BookDTO getBookById(int idBook) {

        Optional<BookDTO> bookDTOOptional = bookDAO.getBookById(idBook);
        if(!bookDTOOptional.isPresent()) {
            return null;
        }

        BookDTO book = bookDTOOptional.get();
        book.setCategories(categoryDAO.getCategoriesByBookId(book.getIdBook()));

        Optional<WriterDTO> writerDTOOptional = writerDAO.getWriterById(book.getWriter().getIdWriter());
        book.setWriter(null);
        writerDTOOptional.ifPresent(book::setWriter);

        Optional<PublisherDTO> publisherDTOOptional = publisherDAO.getPublisherById(book.getPublisher().getIdPublisher());
        book.setPublisher(null);
        publisherDTOOptional.ifPresent(book::setPublisher);

        return book;

    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        Comparator<CategoryDTO> comparator = Comparator.comparing(CategoryDTO::getDescription);
        return categoryDAO.getAllCategories().stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getMaxBookPrice() {
        return bookDAO.getMaxBookPrice();
    }

    @Override
    public List<BookDashboardTextFilter> getAllEntriesForFilter() {
        return bookDAO.getAllBookDashboardFilter();
    }
}

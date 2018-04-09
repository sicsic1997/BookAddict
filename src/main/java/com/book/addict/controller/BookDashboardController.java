package com.book.addict.controller;

import com.book.addict.domain.BookDashboardFilter;
import com.book.addict.dto.BookDTO;
import com.book.addict.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.Response;
import java.util.List;

@Controller
@RequestMapping("bookAddict/bookDashboard/")
public class BookDashboardController {

    @Autowired
    BookService bookService;

    @RequestMapping(
            value = "filter",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBooksByFilter(@RequestBody BookDashboardFilter bookDashboardFilter) {

        List<BookDTO> bookList = bookService.getBooksByFilter(bookDashboardFilter);
        return new ResponseEntity<Object>(bookList, HttpStatus.OK);

    }

    @RequestMapping(
            value = "getMaxBookPrice",
            method = RequestMethod.GET)
    public ResponseEntity<Object> getMaxBookPrice() {
        return new ResponseEntity<Object>(bookService.getMaxBookPrice(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "getAllCategories",
            method = RequestMethod.GET)
    public ResponseEntity<Object> getAllCategories() {
        return new ResponseEntity<Object>(bookService.getAllCategories(), HttpStatus.OK);
    }

}

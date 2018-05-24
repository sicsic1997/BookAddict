package com.book.addict.controller;

import com.book.addict.domain.BookingItemWrapper;
import com.book.addict.dto.BookingItemDTO;
import com.book.addict.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("bookAddict/bookings/")
public class BookingController {

    @Autowired
    BookingService bookingService;


    @RequestMapping(
            value = "saveBooking",
            method = RequestMethod.POST)
    public ResponseEntity saveBooking(@RequestParam int userId,
                                      @RequestBody BookingItemWrapper bookList,
                                      @RequestParam String description) {
        bookingService.saveBooking(userId, bookList.getBookingItemDTOList(), description);
        return new ResponseEntity(HttpStatus.OK);
    }
}

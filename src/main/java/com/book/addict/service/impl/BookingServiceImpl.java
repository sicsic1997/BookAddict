package com.book.addict.service.impl;

import com.book.addict.dao.BookingDAO;
import com.book.addict.dto.BookingItemDTO;
import com.book.addict.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Override
    public void saveBooking(int userId, List<BookingItemDTO> bookingItemDTOS, String description) {
        bookingDAO.saveBooking(userId, bookingItemDTOS, description);
    }

}

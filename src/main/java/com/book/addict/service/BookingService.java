package com.book.addict.service;

import com.book.addict.dto.BookingItemDTO;

import java.util.List;

public interface BookingService {

    void saveBooking(int userId, List<BookingItemDTO> bookingItemDTOS, String description);

}

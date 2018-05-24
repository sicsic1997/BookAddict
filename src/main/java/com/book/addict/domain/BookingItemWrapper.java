package com.book.addict.domain;

import com.book.addict.dto.BookingItemDTO;

import java.util.List;

public class BookingItemWrapper {
    private List<BookingItemDTO> bookingItemDTOList;

    public List<BookingItemDTO> getBookingItemDTOList() {
        return bookingItemDTOList;
    }

    public void setBookingItemDTOList(List<BookingItemDTO> bookingItemDTOList) {
        this.bookingItemDTOList = bookingItemDTOList;
    }
}

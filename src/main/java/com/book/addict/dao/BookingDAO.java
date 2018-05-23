package com.book.addict.dao;

import com.book.addict.dto.BookingItemDTO;
import java.util.List;

public interface BookingDAO {

    void saveBooking(int userId, List<BookingItemDTO> bookingItemDTOList, String description);

}

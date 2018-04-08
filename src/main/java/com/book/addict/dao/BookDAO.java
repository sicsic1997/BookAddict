package com.book.addict.dao;

import com.book.addict.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookDAO {

    Optional<BookDTO> getBookById(int bookId);

    List<Integer> getAllBookIds();

}

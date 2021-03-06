package com.book.addict.dao;

import com.book.addict.dto.WriterDTO;

import java.util.List;
import java.util.Optional;

public interface WriterDAO {

    Optional<WriterDTO> getWriterById(int idWriter);

    List<String> getAllWritersDe();

}

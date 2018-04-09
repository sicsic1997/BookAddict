package com.book.addict.dao;

import com.book.addict.dto.PublisherDTO;

import java.util.Optional;

public interface PublisherDAO {

    Optional<PublisherDTO> getPublisherById(int idPublisher);

}

package com.book.addict.service;

import com.book.addict.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    UserDTO getUserByUserName(String userName, String password);

    void registerUser(UserDTO userDTO);

}

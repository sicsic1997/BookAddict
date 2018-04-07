package com.book.addict.service;

import com.book.addict.dto.UserDTO;

public interface UserService {

    UserDTO getUserByUserName(String userName, String password);

    Boolean registerUser(UserDTO userDTO);

}

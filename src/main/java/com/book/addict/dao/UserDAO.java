package com.book.addict.dao;


import com.book.addict.dto.UserDTO;

import java.util.Optional;

public interface UserDAO {

    Optional<UserDTO> getUserByUserName(String userName);

    void registerUser(UserDTO user);

}

package com.book.addict.service.impl;

import com.book.addict.dao.UserDAO;
import com.book.addict.dto.UserDTO;
import com.book.addict.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDTO getUserByUserName(String userName, String password) {

        Optional<UserDTO> userDTO =  userDAO.getUserByUserName(userName);
        UserDTO user = null;
        if(userDTO.isPresent()) {
            user = userDTO.get();
            if(user.getPassword().equals(password)) {
                return user;
            } else {
                return null;
            }
        }
        return null;

    }

    @Override
    public void registerUser(UserDTO userDTO) {
        userDAO.registerUser(userDTO);
    }
}

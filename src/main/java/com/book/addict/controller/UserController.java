package com.book.addict.controller;

import com.book.addict.dto.UserDTO;
import com.book.addict.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("bookAddict/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET)
    public ResponseEntity<Object> loginUser(@RequestParam String userName, @RequestParam String password) {

        UserDTO userDTO = userService.getUserByUserNameAndPassword(userName, password);
        if(userDTO == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
        }

    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO) {

        String responseMessage = null;
        Boolean isSuccessful = userService.registerUser(userDTO);
        if(isSuccessful) {
            return new ResponseEntity<Object>("User successful registered", HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("User already exists in the database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
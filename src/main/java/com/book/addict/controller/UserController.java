package com.book.addict.controller;

import com.book.addict.dto.UserDTO;
import com.book.addict.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
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
    public ResponseEntity registerUser(@RequestBody UserDTO userDTO) {

        String responseMessage = null;
        Boolean isSuccessful = userService.registerUser(userDTO);
        if(isSuccessful) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

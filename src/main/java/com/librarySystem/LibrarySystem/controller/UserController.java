package com.librarySystem.LibrarySystem.controller;

import com.librarySystem.LibrarySystem.DTO.UserDTO;
import com.librarySystem.LibrarySystem.entities.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("/user/register")
    public User registerUser(@RequestBody UserDTO userDTO){


    return null;
    }


}

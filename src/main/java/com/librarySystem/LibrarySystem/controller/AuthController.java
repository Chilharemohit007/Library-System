package com.librarySystem.LibrarySystem.controller;

import com.librarySystem.LibrarySystem.DTO.RegisterDTO;
import com.librarySystem.LibrarySystem.security.JwtUtil;
import com.librarySystem.LibrarySystem.service.MyUserDetailsService;
import com.librarySystem.LibrarySystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import javax.servlet.http.HttpServlet;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {
        dto.setRoles(Collections.singleton("USER"));
        userService.registerUser(dto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterDTO dto) {
        dto.setRoles(Collections.singleton("ADMIN"));
        userService.registerUser(dto);
        return new ResponseEntity<>("Admin registered successfully", HttpStatus.CREATED);
    }
}

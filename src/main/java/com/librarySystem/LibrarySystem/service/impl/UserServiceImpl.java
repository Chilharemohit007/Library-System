package com.librarySystem.LibrarySystem.service.impl;

import com.librarySystem.LibrarySystem.DTO.RegisterDTO;
import com.librarySystem.LibrarySystem.entities.Roles;
import com.librarySystem.LibrarySystem.entities.User;
import com.librarySystem.LibrarySystem.exception.ResourceNotFoundException;
import com.librarySystem.LibrarySystem.repositories.RolesRepository;
import com.librarySystem.LibrarySystem.repositories.UserRepository;
import com.librarySystem.LibrarySystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired(required = true)
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public void registerUser(RegisterDTO dto) {

       if( userRepo.findByUsername(dto.getUsername()).isPresent()){
           throw new RuntimeException("Username already exists");
       }

       User user = new User();
       user.setUsername(dto.getUsername());
       user.setEmail(dto.getEmail());
       user.setPassword(passwordEncoder.encode(dto.getPassword()));
       user.setMobile(dto.getMobile());

        Set<Roles> roleName = dto.getRoles().stream().map(role -> rolesRepository.findByRole("ROLE_" + role).orElseThrow(
                () -> new ResourceNotFoundException("Role Not Found...!", "Role")
        )).collect(Collectors.toSet());
        System.out.println("Looking for role: ROLE_" + roleName);

        user.setRoles(roleName);

        userRepo.save(user);

    }
}

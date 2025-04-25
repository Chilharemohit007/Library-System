package com.librarySystem.LibrarySystem.repositories;

import com.librarySystem.LibrarySystem.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByRole(String role);
}

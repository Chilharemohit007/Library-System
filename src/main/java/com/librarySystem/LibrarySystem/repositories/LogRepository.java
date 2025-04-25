package com.librarySystem.LibrarySystem.repositories;

import com.librarySystem.LibrarySystem.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}

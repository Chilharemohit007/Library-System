package com.librarySystem.LibrarySystem.repositories;

import com.librarySystem.LibrarySystem.entities.RecentReturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentReturnRepository extends JpaRepository<RecentReturn, Long> {
}

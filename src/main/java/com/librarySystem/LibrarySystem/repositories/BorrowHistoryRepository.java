package com.librarySystem.LibrarySystem.repositories;

import com.librarySystem.LibrarySystem.entities.BorrowHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Long> {

    List<BorrowHistory> findByUserUsername(Long username);

    @Query("SELECT b FROM BorrowHistory b WHERE b.user.id =:user_id")
    List<BorrowHistory> findByUserId(@Param("user_id") Long userId);

    Page<BorrowHistory> findByUserId(Long userId, Pageable pageable);
}

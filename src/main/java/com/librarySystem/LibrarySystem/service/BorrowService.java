package com.librarySystem.LibrarySystem.service;

import com.librarySystem.LibrarySystem.DTO.BorrowHistoryDTO;
import com.librarySystem.LibrarySystem.DTO.BorrowRequestDTO;
import com.librarySystem.LibrarySystem.entities.BorrowHistory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BorrowService {
    BorrowRequestDTO borrowBook(String username, String bookId);

    List<BorrowHistoryDTO> getBorrowHistoryByUser(Long userId);

    Page<BorrowHistoryDTO> getBorrowHistoryPageByUser(Long userId, int page, int size);
}

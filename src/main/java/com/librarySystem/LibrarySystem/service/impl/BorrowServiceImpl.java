package com.librarySystem.LibrarySystem.service.impl;

import com.librarySystem.LibrarySystem.DTO.BorrowHistoryDTO;
import com.librarySystem.LibrarySystem.DTO.BorrowRequestDTO;

import com.librarySystem.LibrarySystem.entities.Book;
import com.librarySystem.LibrarySystem.entities.BorrowHistory;
import com.librarySystem.LibrarySystem.entities.User;
import com.librarySystem.LibrarySystem.exception.ResourceNotFoundException;
import com.librarySystem.LibrarySystem.repositories.BookRepository;
import com.librarySystem.LibrarySystem.repositories.BorrowHistoryRepository;
import com.librarySystem.LibrarySystem.repositories.UserRepository;
import com.librarySystem.LibrarySystem.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowHistoryRepository borrowHistoryRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookRepository bookRepo;

    @Override
    public BorrowRequestDTO borrowBook(String username, String bookId) {

        User user = userRepo.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("Username Not Found...!", "User")
        );

        Book book = bookRepo.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book Not Found...!", "Book")
        );

        BorrowHistory borrowHistory = new BorrowHistory();
        borrowHistory.setUser(user);
        borrowHistory.setBook(book);
        borrowHistory.setBorrowedAt(LocalDateTime.now());

        BorrowHistory savedBorrowedBook = borrowHistoryRepository.save(borrowHistory);
        BorrowRequestDTO borrowRequestDTO = mapToBorrowRequestDTO(savedBorrowedBook);
        return borrowRequestDTO;
    }

    @Override
    public List<BorrowHistoryDTO> getBorrowHistoryByUser(Long userId) {
        List<BorrowHistory> borrowHistoryList = borrowHistoryRepository.findByUserId(userId);
       /* List<BorrowHistory> borrowHistoryList = borrowHistoryRepository.findByUser_Id(userId);*/

        return borrowHistoryList.stream().map(this::mapToBorrowHistoryDTO).collect(Collectors.toList());
    }

    @Override
    public Page<BorrowHistoryDTO> getBorrowHistoryPageByUser(Long userId, int page, int size) {

        Page<BorrowHistory> pagedHistory = borrowHistoryRepository.findByUserId(userId, PageRequest.of(page, size));

       /* Option 1 :*/
        /*List<BorrowHistoryDTO> borrowHistoryDTOList = pagedHistory.getContent().stream().map(this::mapToBorrowHistoryDTO).collect(Collectors.toList());

        return new PageImpl<>(borrowHistoryDTOList, PageRequest.of(page, size), pagedHistory.getTotalElements());*/

        /* Option 2 :*/
       return pagedHistory.map(this::mapToBorrowHistoryDTO);
    }

    public BorrowRequestDTO mapToBorrowRequestDTO(BorrowHistory borrowHistory){
        BorrowRequestDTO br = new BorrowRequestDTO();
        br.setUsername(borrowHistory.getUser().getUsername());
        br.setBookId(borrowHistory.getBook().getBookId());
        return br;
    }

    public BorrowHistoryDTO mapToBorrowHistoryDTO(BorrowHistory borrowHistory){
        BorrowHistoryDTO borrowHistoryDTO = new BorrowHistoryDTO();
        borrowHistoryDTO.setId(borrowHistory.getId());
        borrowHistoryDTO.setBookId(borrowHistory.getBook().getBookId());
        borrowHistoryDTO.setUsername(borrowHistory.getUser().getUsername());
        borrowHistoryDTO.setBookTitle(borrowHistory.getBook().getTitle());
        borrowHistoryDTO.setBorrowedAt(borrowHistory.getBorrowedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return borrowHistoryDTO;
    }
}

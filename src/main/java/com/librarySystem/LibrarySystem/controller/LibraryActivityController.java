package com.librarySystem.LibrarySystem.controller;

import com.librarySystem.LibrarySystem.DTO.BorrowHistoryDTO;
import com.librarySystem.LibrarySystem.DTO.BorrowRequest;
import com.librarySystem.LibrarySystem.DTO.BorrowRequestDTO;
import com.librarySystem.LibrarySystem.entities.BorrowHistory;
import com.librarySystem.LibrarySystem.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTMLDocument;
import java.util.List;

@RestController
@RequestMapping("/api/libraryActivity")
public class LibraryActivityController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/borrow")
    public ResponseEntity<String> borrow(@RequestBody BorrowRequestDTO borrowRequestDTO){
        if((borrowRequestDTO.getUsername()!=null && borrowRequestDTO.getBookId()!=null) ||
        !(borrowRequestDTO.getUsername().isEmpty() && borrowRequestDTO.getBookId().isEmpty())) {
            BorrowRequestDTO borrowRequestDTO1 = borrowService.borrowBook(borrowRequestDTO.getUsername(), borrowRequestDTO.getBookId());
            return new ResponseEntity<>("Books Borrow Successfully...!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Username and Book", HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/borrow-history")
    public ResponseEntity<List<BorrowHistoryDTO>> getBorrowHistoryByUser(@RequestParam(name = "userId") Long userId){
        if(userId!=null) {
            List<BorrowHistoryDTO> borrowHistoryList = borrowService.getBorrowHistoryByUser(userId);
            return new ResponseEntity<>(borrowHistoryList, HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/borrow-history-pagination")
    public ResponseEntity<Page<BorrowHistoryDTO>> getBorrowHistoryPaginationByUser(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        if(userId!=null) {
            Page<BorrowHistoryDTO> borrowHistoryList = borrowService.getBorrowHistoryPageByUser(userId, page, size);
            return new ResponseEntity<>(borrowHistoryList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

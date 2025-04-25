package com.librarySystem.LibrarySystem.DTO;


public class BorrowRequest {
    private String username;
    private Long bookId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}

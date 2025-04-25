package com.librarySystem.LibrarySystem.DTO;

public class BorrowRequestDTO {

    private String username;

    private String bookId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}

package com.librarySystem.LibrarySystem.service;

import com.librarySystem.LibrarySystem.DTO.BookDTO;


import java.util.List;

public interface BookService {
    BookDTO addBook(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    BookDTO getBookByIdOrName(String idOrName);

    void deleteBookDetails(String idOrName);

    BookDTO updateBookDetails(String id, BookDTO bookDTO);

    List<BookDTO> getBookBySearch(String titleOrAuthor);
}

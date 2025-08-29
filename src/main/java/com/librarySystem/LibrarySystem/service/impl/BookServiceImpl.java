package com.librarySystem.LibrarySystem.service.impl;

import com.librarySystem.LibrarySystem.DTO.BookDTO;
import com.librarySystem.LibrarySystem.entities.Book;
import com.librarySystem.LibrarySystem.exception.ResourceNotFoundException;
import com.librarySystem.LibrarySystem.repositories.BookRepository;
import com.librarySystem.LibrarySystem.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {


    @Autowired
    private BookRepository bookRepo;

    @Override
    public BookDTO addBook(BookDTO bookDTO) {

        Book book = new Book();
        book.setBookId(generateShortId(bookDTO.getTitle()));
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        Book savedBook = bookRepo.save(book);
        BookDTO savedBookDTO = mapToDTO(savedBook);
        return savedBookDTO;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        //AOP for testing purpose
        //getBookById(-1);

        List<Book> allBooks = bookRepo.findAll();
        //List<BookDTO> collect = all.stream().map(m -> mapToDTO(m)).collect(Collectors.toList());
        if(allBooks.isEmpty()){
            throw new ResourceNotFoundException("No Books", "Book");
        }else {
            return allBooks.stream().map(this::mapToDTO).collect(Collectors.toList());
        }

    }

    public String getBookById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Book ID must be positive");
        }
        if (id == 1) {
            return "Spring in Action";
        } else if (id == 2) {
            return "Effective Java";
        }
        return "Book Not Found!";
    }


    @Override
    public BookDTO getBookByIdOrName(String idOrName) {

        Book bookDetails = bookRepo.findBookByIdOrName(idOrName);
        if(bookDetails == null){
            throw new ResourceNotFoundException("Book Not Found With IdOrName : "+idOrName, "Book");
        }else {
            return mapToDTO(bookDetails);
        }
    }

    @Override
    public void deleteBookDetails(String idOrName) {
        Book bookDetails = bookRepo.findBookByIdOrName(idOrName);
        if(bookDetails == null){
            throw new ResourceNotFoundException("Book Not Found With IdOrName : "+idOrName, "Book");
        }else {
            bookRepo.deleteById(bookDetails.getBookId());
        }
    }

    @Override
    public BookDTO updateBookDetails(String id, BookDTO bookDTO) {

        Book bookDetails = bookRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book Not Found With Id : "+id, "Book")
        );

        Book book = new Book();
        book.setBookId(bookDetails.getBookId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        Book save = bookRepo.save(book);
        BookDTO bookDTO1 = mapToDTO(save);
        return  bookDTO1;
    }

    @Override
    public List<BookDTO> getBookBySearch(String titleOrAuthor) {

        List<Book> bookBySearch = bookRepo.findBookBySearch(titleOrAuthor);
        if(!bookBySearch.isEmpty()){
            List<BookDTO> bookDTOList = bookBySearch.stream().map(this::mapToDTO).collect(Collectors.toList());
            return bookDTOList;
        }else {
            throw new ResourceNotFoundException("Not Data Found", "Search Bar");
        }

    }


    private String generateShortId(String title) {
        UUID uuid = UUID.randomUUID();
        long leastSigBits = uuid.getLeastSignificantBits();
        String base62 = Long.toString(Math.abs(leastSigBits), 36); // Base36 or implement full Base62
        return title.replaceAll("\\s+", "") + base62;
    }


    private BookDTO mapToDTO(Book savedBook) {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(savedBook.getBookId());
        bookDTO.setAuthor(savedBook.getAuthor());
        bookDTO.setTitle(savedBook.getTitle());
        return bookDTO;
    }
}

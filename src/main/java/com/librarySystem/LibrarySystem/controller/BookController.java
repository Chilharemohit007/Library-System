package com.librarySystem.LibrarySystem.controller;

import com.librarySystem.LibrarySystem.DTO.BookDTO;
import com.librarySystem.LibrarySystem.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addBook")
    public ResponseEntity<BookDTO> saveBooks(@RequestBody BookDTO bookDTO){
        BookDTO savedBook = bookService.addBook(bookDTO);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/allBooks")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> listOfBooks = bookService.getAllBooks();
        if(!(listOfBooks.isEmpty())){
            return new ResponseEntity<>(listOfBooks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getBookByIdOrName/{idOrName}")
    public ResponseEntity<BookDTO> getBookByIdOrName(@PathVariable(name = "idOrName") String idOrName){
        if(idOrName!=null){
            BookDTO bookDetails = bookService.getBookByIdOrName(idOrName);
            return new ResponseEntity<>(bookDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/remove/{idOrName}")
    public ResponseEntity<String> deleteBookBy(@PathVariable(name = "idOrName") String idOrName){
        if(idOrName!=null) {
            bookService.deleteBookDetails(idOrName);
            return new ResponseEntity<>("Book Details Deleted Successfully...!", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/modify/{id}")
    public ResponseEntity<BookDTO> updateBookDetails(@PathVariable(name = "id") String id, @RequestBody BookDTO bookDTO){
        BookDTO updatedBookDetails = bookService.updateBookDetails(id, bookDTO);
        return new ResponseEntity<>(updatedBookDetails, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/searchBar/{titleOrAuthor}")
    public ResponseEntity<List<BookDTO>> getBySearch(@PathVariable(name = "titleOrAuthor") String titleOrAuthor){
        List<BookDTO> bookDTOList = bookService.getBookBySearch(titleOrAuthor);
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }
}











/*@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<BookDTO> saveBooks(@RequestBody BookDTO bookDTO){

        BookDTO savedBook = bookService.addBook(bookDTO);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> listOfBooks = bookService.getAllBooks();
        if(!(listOfBooks.isEmpty())){
            return new ResponseEntity<>(listOfBooks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getBookByIdOrName/{idOrName}")
    public ResponseEntity<BookDTO> getBookByIdOrName(@PathVariable(name = "idOrName") String idOrName){
        if(idOrName!=null){
            BookDTO bookDetails = bookService.getBookByIdOrName(idOrName);
            return new ResponseEntity<>(bookDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/remove/{idOrName}")
    public ResponseEntity<String> deleteBookBy(@PathVariable(name = "idOrName") String idOrName){
        if(idOrName!=null) {
            bookService.deleteBookDetails(idOrName);
            return new ResponseEntity<>("Book Details Deleted Successfully...!", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<BookDTO> updateBookDetails(@PathVariable(name = "id") String id, @RequestBody BookDTO bookDTO){

        BookDTO updatedBookDetails = bookService.updateBookDetails(id, bookDTO);
        return new ResponseEntity<>(updatedBookDetails, HttpStatus.OK);
    }

    @GetMapping("/searchBar/{titleOrAuthor}")
    public ResponseEntity<List<BookDTO>> getBySearch(@PathVariable(name = "titleOrAuthor") String titleOrAuthor){

        List<BookDTO> bookDTOList = bookService.getBookBySearch(titleOrAuthor);

        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

}*/


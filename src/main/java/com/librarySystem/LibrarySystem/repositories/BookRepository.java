package com.librarySystem.LibrarySystem.repositories;

import com.librarySystem.LibrarySystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {


    @Query("SELECT b FROM Book b WHERE b.bookId =:idOrName OR b.title =:idOrName ")
    Book findBookByIdOrName(@Param("idOrName") String idOrName);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :titleOrAuthor, '%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%', :titleOrAuthor, '%'))")
    List<Book> findBookBySearch(@Param("titleOrAuthor") String titleOrAuthor);

}

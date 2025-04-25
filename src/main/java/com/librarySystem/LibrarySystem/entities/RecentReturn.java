package com.librarySystem.LibrarySystem.entities;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recent_returns")
public class RecentReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime returnedAt = LocalDateTime.now();
}



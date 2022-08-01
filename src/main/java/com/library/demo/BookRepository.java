package com.library.demo;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingOrContentContaining(String searchTerm, String searchTerm2);

    
}

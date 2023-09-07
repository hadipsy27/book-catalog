package com.labs.catalog.config;

import com.labs.catalog.domain.Author;
import com.labs.catalog.domain.Book;
import com.labs.catalog.repository.BookRepository;
import com.labs.catalog.repository.impl.BookRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Author author(){
        return new Author(1L, "Hadi Prasetyo", -16401L);
    }

    @Bean
    public Book book1(Author author){
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Bumi Manusia");
        book.setDescription("Adalah salah satu novel bagus");
        book.setAuthor(author);
        return book;
    }

    @Bean
    public Book book2(Author author){
        Book book = new Book();
        book.setId(2L);
        book.setTitle("Arok Dedes");
        book.setDescription("Adalah novel juga");
        book.setAuthor(author);
        return book;
    }

    @Bean
    public BookRepository bookRepository(Book book1, Book book2){
        Map<Long, Book> bookMap = new HashMap<Long, Book>();
        bookMap.put(1L, book1);
        bookMap.put(2L, book2);

        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
        bookRepository.setBookMap(bookMap);

        return bookRepository;
    }
}

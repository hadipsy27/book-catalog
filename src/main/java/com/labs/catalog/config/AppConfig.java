package com.labs.catalog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.catalog.security.util.JwtTokenFactory;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Key;

@Configuration
public class AppConfig {

//    @Bean
//    public Author author(){
//        return new Author(1L, "Hadi Prasetyo", null, false);
//    }
//
//    @Bean
//    public Book book1(Author author){
//        Book book = new Book();
//        book.setId(1L);
//        book.setTitle("Bumi Manusia");
//        book.setDescription("Adalah salah satu novel bagus");
//        book.setAuthor(author);
//        return book;
//    }
//
//    @Bean
//    public Book book2(Author author){
//        Book book = new Book();
//        book.setId(2L);
//        book.setTitle("Arok Dedes");
//        book.setDescription("Adalah novel juga");
//        book.setAuthor(author);
//        return book;
//    }
//
//    @Bean
//    public BookRepository bookRepository(Book book1, Book book2){
//        Map<Long, Book> bookMap = new HashMap<Long, Book>();
//        bookMap.put(1L, book1);
//        bookMap.put(2L, book2);
//
//        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
//        bookRepository.setBookMap(bookMap);
//
//        return bookRepository;
//    }

    @Bean
    public Key key(){
        byte[] keyBytes = Decoders.BASE64.decode("kjasdkfjasdkfa878kjhkjh435ghfhgf32hgjhkj23456hjghfd4231gkhjsdlkjnasdklfja8342kjlsdf");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Bean
    public JwtTokenFactory jwtTokeFactory(Key key){
        return new JwtTokenFactory(key);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}

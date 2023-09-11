package com.labs.catalog.web;

import com.labs.catalog.dto.BookCreateDTO;
import com.labs.catalog.dto.BookDetailDto;
import com.labs.catalog.dto.BookUpdateRequestDTO;
import com.labs.catalog.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class BookResource {

    private final BookService bookService;

    @GetMapping("/book/{bookId}")
    public BookDetailDto findBookDetail(@PathVariable("bookId") Long id){
        StopWatch stopWatch = new StopWatch();
        log.info("Start find book detail " + id);

        stopWatch.start();

        BookDetailDto result = bookService.findBookDetailById(id);
        log.info("Finish book detail excution time = {}" , stopWatch.getTotalTimeMillis());

        return result;
    }

    @PostMapping("/book")
    public ResponseEntity<Void> createANewBook(@RequestBody BookCreateDTO bookCreateDTO){
        bookService.createNewBook(bookCreateDTO);
        return ResponseEntity.created(URI.create("/book")).build();

    }

    @GetMapping("/book")
    public ResponseEntity<List<BookDetailDto>> findBookList(){
        return ResponseEntity.ok().body(bookService.findBookDetail());
    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable Long bookId, @RequestBody BookUpdateRequestDTO dto){
        bookService.updateBook(bookId, dto);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}

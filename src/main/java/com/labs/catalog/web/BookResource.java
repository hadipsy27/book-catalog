package com.labs.catalog.web;

import com.labs.catalog.dto.BookCreateDTO;
import com.labs.catalog.dto.BookDetailResponseDTO;
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
@RequestMapping("/v1")
public class BookResource {

    private final BookService bookService;

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookDetailResponseDTO> findBookDetail(@PathVariable("bookId") String id){
        StopWatch stopWatch = new StopWatch();
        log.info("Start find book detail " + id);

        stopWatch.start();

        BookDetailResponseDTO result = bookService.findBookDetailById(id);
        log.info("Finish book detail excution time = {}" , stopWatch.getTotalTimeMillis());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/book")
    public ResponseEntity<Void> createANewBook(@RequestBody BookCreateDTO bookCreateDTO){
        bookService.createNewBook(bookCreateDTO);
        return ResponseEntity.created(URI.create("/book")).build();

    }

    @GetMapping("/book")
    public ResponseEntity<List<BookDetailResponseDTO>> findBookList(){
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

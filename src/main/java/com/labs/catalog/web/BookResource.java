package com.labs.catalog.web;

import com.labs.catalog.dto.*;
import com.labs.catalog.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
public class BookResource {

    private final BookService bookService;

    @GetMapping("/v1/book/{bookId}")
    public ResponseEntity<BookDetailResponseDTO> findBookDetail(@PathVariable("bookId") String id){
        StopWatch stopWatch = new StopWatch();
        log.info("Start find book detail " + id);

        stopWatch.start();

        BookDetailResponseDTO result = bookService.findBookDetailById(id);
        log.info("Finish book detail excution time = {}" , stopWatch.getTotalTimeMillis());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/v1/book")
    public ResponseEntity<Void> createANewBook(@RequestBody BookCreateDTO bookCreateDTO){
        bookService.createNewBook(bookCreateDTO);
        return ResponseEntity.created(URI.create("/book")).build();

    }

    @GetMapping("/v1/book")
    public ResponseEntity<List<BookDetailResponseDTO>> findBookList(){
        return ResponseEntity.ok().body(bookService.findBookDetail());
    }

    // Get data book from
    // 1. Title
    // 2. Publisher name -> from publisher Table
    // 3. Author name -> form Author Table
    @GetMapping("/v2/books")
    public ResponseEntity<ResultPageResponseDTO<BookListResponseDTO>> findBookList(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "title") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "bookTitle", required = false, defaultValue = "") String bookTitle,
            @RequestParam(name = "publisherName", required = false, defaultValue = "") String publisherName,
            @RequestParam(name = "authorName", required = false, defaultValue = "") String authorName
    ){
        return ResponseEntity.ok().body(bookService.findBookList(page,limit,sortBy,direction,bookTitle,publisherName, authorName));
    }

    @PutMapping("/v1/book/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable Long bookId, @RequestBody BookUpdateRequestDTO dto){
        bookService.updateBook(bookId, dto);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/v1/book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}

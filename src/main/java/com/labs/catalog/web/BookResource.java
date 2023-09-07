package com.labs.catalog.web;

import com.labs.catalog.dto.BookDetailDto;
import com.labs.catalog.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}

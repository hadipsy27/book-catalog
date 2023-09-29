package com.labs.catalog.service.impl;

import com.labs.catalog.domain.Author;
import com.labs.catalog.domain.Book;
import com.labs.catalog.domain.Category;
import com.labs.catalog.domain.Publisher;
import com.labs.catalog.dto.*;
import com.labs.catalog.exception.BadRequestException;
import com.labs.catalog.repository.BookRepository;
import com.labs.catalog.service.AuthorService;
import com.labs.catalog.service.BookService;
import com.labs.catalog.service.CategoryService;
import com.labs.catalog.service.PublisherService;
import com.labs.catalog.util.PaginationUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    @Override
    public BookDetailResponseDTO findBookDetailById(String secureId) {
        Book book = bookRepository.findBySecureId(secureId).orElseThrow(() -> new BadRequestException("book_id.Invalid"));
        BookDetailResponseDTO bookDetailResponseDTO = new BookDetailResponseDTO();

        bookDetailResponseDTO.setBookId(book.getSecureId());
        bookDetailResponseDTO.setCategories(categoryService.constructDTO(book.getCategories()));
        bookDetailResponseDTO.setBookTitle(book.getTitle());
        bookDetailResponseDTO.setAuthors(authorService.constructDTO(book.getAuthors()));
        bookDetailResponseDTO.setPublisher(publisherService.constructDTO(book.getPublisher()));
        bookDetailResponseDTO.setBookDescription(book.getDescription());
        return bookDetailResponseDTO;
    }

    @Override
    public List<BookDetailResponseDTO> findBookDetail() {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(book -> {
            BookDetailResponseDTO bookDetailResponseDTO = new BookDetailResponseDTO();
//            bookDetailDto.setAuthorName(book.getAuthor().getName());
            bookDetailResponseDTO.setBookTitle(book.getTitle());
            bookDetailResponseDTO.setBookDescription(book.getDescription());
            //bookDetailResponseDTO.setBookId(book.getId());
            return bookDetailResponseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void createNewBook(BookCreateDTO dto) {
        List<Author> authors = authorService.findAuthors(dto.getAuthorId());
        List<Category> categories = categoryService.findCategories(dto.getCategoryList());
        Publisher publisher = publisherService.findPublisher(dto.getPublisherId());

        Book book = new Book();
        book.setAuthors(authors);
        book.setCategories(categories);
        book.setPublisher(publisher);
        book.setTitle(dto.getBookTitle());
        book.setDescription(dto.getDescription());
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long bookId, BookUpdateRequestDTO dto) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BadRequestException("book_id.Invalid"));
        book.setTitle(dto.getBookTitle());
        book.setDescription(dto.getDescription());

        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public ResultPageResponseDTO<BookListResponseDTO> findBookList(Integer page, Integer limit, String sortBy,
                                                                   String direction, String publisherName, String bookTitle) {
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<Book> pageResult = bookRepository.findBookList(bookTitle, publisherName, pageable);
        List<BookListResponseDTO> result = pageResult.stream().map(book -> {
            BookListResponseDTO dto = new BookListResponseDTO();

            dto.setId(book.getSecureId());
            dto.setAuthorNames(book.getAuthors().stream().map(a -> a.getName()).collect(Collectors.toList()));
            dto.setCategoryCodes(book.getCategories().stream().map(c -> c.getCode()).collect(Collectors.toList()));
            dto.setTitle(book.getTitle());
            dto.setPublisherName(book.getPublisher().getName());
            dto.setDescription(book.getDescription());
            return dto;
        }).collect(Collectors.toList());
        return PaginationUtil.createResultPageDTO(result, pageResult.getTotalElements(), pageResult.getTotalPages());
    }
}

package com.labs.catalog.service.impl;

import com.labs.catalog.domain.Author;
import com.labs.catalog.dto.AuthorCreateRequestDTO;
import com.labs.catalog.dto.AuthorResponseDTO;
import com.labs.catalog.dto.AuthorUpdateRequestDTO;
import com.labs.catalog.exception.BadRequestException;
import com.labs.catalog.repository.AuthorRepository;
import com.labs.catalog.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Override
    public AuthorResponseDTO findAuthorBySecureId(String id) {
        Author author = authorRepository.findBySecureId(id).orElseThrow(() -> new BadRequestException("Invalid author id: " + id));
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setAuthorName(author.getName());
        authorResponseDTO.setBirthDate(author.getBirthDate().toEpochDay());
        return authorResponseDTO;
    }

    @Override
    public void createAuthor(List<AuthorCreateRequestDTO> request) {
        List<Author> authors = request.stream().map((dto) -> {
            Author author = new Author();
            author.setName(dto.getAuthorName());
            author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));
            return author;
        }).collect(Collectors.toList());

        authorRepository.saveAll(authors);
    }

    @Override
    public void updateAuthor(String id, AuthorUpdateRequestDTO dto) {
        Author author = authorRepository.findBySecureId(id).orElseThrow(() -> new BadRequestException("Invalid author id: " + id));
        author.setName(dto.getAuthorName() == null ? author.getName() : dto.getAuthorName());
        author.setBirthDate(dto.getBirthDate() == null ? author.getBirthDate() : LocalDate.ofEpochDay(dto.getBirthDate()));
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(String authorId) {
        // Hard Delete
        // Author author = authorRepository.findById(authorId).orElseThrow(() -> new BadRequestException("Invalid author id: " + authorId));
        // authorRepository.deleteById(author.getId());

        // Soft delete
        // 1. select data deleted=false
        // Author author = authorRepository.findByIdAndDeletedFalse(authorId).orElseThrow(() -> new BadRequestException("Invalid author id: " + authorId));
        // 2. update to deleted=true
        // author.setDeleted(Boolean.TRUE);
        // authorRepository.save(author);

        // Didn't need findByIdDeletedFalse to Soft delete again
        // Because now soft delete implement in Author Domain / Entity
        Author author = authorRepository.findBySecureId(authorId).orElseThrow(() -> new BadRequestException("Invalid author id: " + authorId));
        authorRepository.delete(author);
    }
}

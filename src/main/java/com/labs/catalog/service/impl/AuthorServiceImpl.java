package com.labs.catalog.service.impl;

import com.labs.catalog.domain.Author;
import com.labs.catalog.dto.AuthorCreateRequestDTO;
import com.labs.catalog.dto.AuthorResponseDTO;
import com.labs.catalog.exception.BadRequestException;
import com.labs.catalog.repository.AuthorRepository;
import com.labs.catalog.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Override
    public AuthorResponseDTO findAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new BadRequestException("Invalid author id: " + id));
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setAuthorName(author.getName());
        authorResponseDTO.setBirthDate(author.getBirthDate().toEpochDay());
        return authorResponseDTO;
    }

    @Override
    public void createAuthor(AuthorCreateRequestDTO request) {
        Author author = new Author();
        author.setName(request.getAuthorName());
        author.setBirthDate(LocalDate.ofEpochDay(request.getBirthDate()));

        authorRepository.save(author);
    }
}

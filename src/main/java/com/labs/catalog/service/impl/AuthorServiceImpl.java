package com.labs.catalog.service.impl;

import com.labs.catalog.domain.Address;
import com.labs.catalog.domain.Author;
import com.labs.catalog.dto.AuthorCreateRequestDTO;
import com.labs.catalog.dto.AuthorResponseDTO;
import com.labs.catalog.dto.AuthorUpdateRequestDTO;
import com.labs.catalog.exception.BadRequestException;
import com.labs.catalog.exception.ResourceNotFoundException;
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
        Author author = authorRepository.findBySecureId(id).orElseThrow(() -> new ResourceNotFoundException("Invalid author id: " + id));
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

            List<Address> addresses = dto.getAddresses().stream().map(ad -> {
                Address address = new Address();
                address.setCityName(ad.getCityName());
                address.setStreetName(ad.getStreetName());
                address.setZipCode(ad.getZipCode());
                address.setAuthor(author);
                return address;
            }).collect(Collectors.toList());
            author.setAddresses(addresses);

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

    @Override
    public List<Author> findAuthors(List<String> authorIdList) {
        List<Author> authors = authorRepository.findBySecureIdIn(authorIdList);
        if (authors.isEmpty()) throw new BadRequestException(("author cant be empty"));
        return authors;
    }

    @Override
    public List<AuthorResponseDTO> constructDTO(List<Author> authors) {
        // Parshing List Author to AuthorResponseDTO
        return authors.stream().map((a -> {
            AuthorResponseDTO responseDTO = new AuthorResponseDTO();
            responseDTO.setAuthorName(a.getName());
            responseDTO.setBirthDate(a.getBirthDate().toEpochDay());
            return responseDTO;
        })).collect(Collectors.toList());
    }
}

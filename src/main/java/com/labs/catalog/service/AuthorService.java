package com.labs.catalog.service;

import com.labs.catalog.domain.Author;
import com.labs.catalog.dto.AuthorCreateRequestDTO;
import com.labs.catalog.dto.AuthorResponseDTO;
import com.labs.catalog.dto.AuthorUpdateRequestDTO;

import java.util.List;
import java.util.Map;

public interface AuthorService {

    public AuthorResponseDTO findAuthorBySecureId(String id);

    public void createAuthor(List<AuthorCreateRequestDTO> request);

    public void updateAuthor(String id, AuthorUpdateRequestDTO dto);

    public void deleteAuthor(String authorId);

    public List<Author> findAuthors(List<String> authorIdList);

    List<AuthorResponseDTO> constructDTO(List<Author> authors);

    Map<Long, List<String>> findAuthorMaps(List<Long> bookIdList);
}

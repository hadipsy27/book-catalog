package com.labs.catalog.service;

import com.labs.catalog.dto.AuthorCreateRequestDTO;
import com.labs.catalog.dto.AuthorResponseDTO;
import com.labs.catalog.dto.AuthorUpdateRequestDTO;

import java.util.List;

public interface AuthorService {

    public AuthorResponseDTO findAuthorById(Long id);

    public void createAuthor(List<AuthorCreateRequestDTO> request);

    public void updateAuthor(Long id, AuthorUpdateRequestDTO dto);
}

package com.labs.catalog.service;

import com.labs.catalog.dto.AuthorCreateRequestDTO;
import com.labs.catalog.dto.AuthorResponseDTO;

public interface AuthorService {

    public AuthorResponseDTO findAuthorById(Long id);

    public void createAuthor(AuthorCreateRequestDTO request);
}

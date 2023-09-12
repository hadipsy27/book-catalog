package com.labs.catalog.web;

import com.labs.catalog.dto.AuthorResponseDTO;
import com.labs.catalog.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthorResouce {

    private AuthorService authorService;

    @GetMapping("/author/{id}/detail")
    public ResponseEntity<AuthorResponseDTO> findAuthorById(@PathVariable Long id){
        AuthorResponseDTO result = authorService.findAuthorById(id);
        return ResponseEntity.ok().body(result);
    }
}

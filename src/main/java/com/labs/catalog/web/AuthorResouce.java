package com.labs.catalog.web;

import com.labs.catalog.domain.Author;
import com.labs.catalog.dto.AuthorCreateRequestDTO;
import com.labs.catalog.dto.AuthorResponseDTO;
import com.labs.catalog.dto.AuthorUpdateRequestDTO;
import com.labs.catalog.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
public class AuthorResouce {

    private AuthorService authorService;

    @GetMapping("/author/{id}/detail")
    public ResponseEntity<AuthorResponseDTO> findAuthorById(@PathVariable Long id){
        AuthorResponseDTO result = authorService.findAuthorById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/author")
    public ResponseEntity<AuthorResponseDTO> createNewAuthor(@RequestBody @Valid List<AuthorCreateRequestDTO> requestDTO){
        authorService.createAuthor(requestDTO);
        return ResponseEntity.created(URI.create("/author")).build();
    }

    @PutMapping("/author/{authorId}")
    public ResponseEntity<Void> updateAuthor(@PathVariable Long authorId, @RequestBody AuthorUpdateRequestDTO dto){
        authorService.updateAuthor(authorId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/author/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId){
        authorService.deleteAuthor(authorId);
        return ResponseEntity.ok().build();
    }
}

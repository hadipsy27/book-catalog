package com.labs.catalog.web;

import com.labs.catalog.dto.AuthorCreateRequestDTO;
import com.labs.catalog.dto.AuthorResponseDTO;
import com.labs.catalog.dto.AuthorUpdateRequestDTO;
import com.labs.catalog.service.AuthorService;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/v1")
@SecurityRequirement(name = "bearerAuth")
public class AuthorResource {

    private AuthorService authorService;

    @GetMapping("/author/{id}/detail")
    public ResponseEntity<AuthorResponseDTO> findAuthorById(@PathVariable String id){
        AuthorResponseDTO result = authorService.findAuthorBySecureId(id);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/author")
    public ResponseEntity<AuthorResponseDTO> createNewAuthor(@RequestBody @Valid List<AuthorCreateRequestDTO> requestDTO){
        authorService.createAuthor(requestDTO);
        return ResponseEntity.created(URI.create("/author")).build();
    }

    @PutMapping("/author/{authorId}")
    public ResponseEntity<Void> updateAuthor(@PathVariable String authorId, @RequestBody AuthorUpdateRequestDTO dto){
        authorService.updateAuthor(authorId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/author/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String authorId){
        authorService.deleteAuthor(authorId);
        return ResponseEntity.ok().build();
    }
}

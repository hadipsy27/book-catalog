package com.labs.catalog.web;

import com.labs.catalog.dto.PublisherCreateRequestDTO;
import com.labs.catalog.dto.PublisherUpdateRequestDTO;
import com.labs.catalog.service.PublisherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
public class PublisherResource {

    private final PublisherService publisherService;

    @PostMapping("/v1/publisher")
    public ResponseEntity<Void> createNewPublisher(@RequestBody @Valid PublisherCreateRequestDTO requestDTO){
        publisherService.createPublisher(requestDTO);

        return ResponseEntity.created(URI.create("/v1/publisher")).build();
    }

    @PutMapping("/v1/publisher/{publisherId}")
    public ResponseEntity<Void> updatePublisher(@PathVariable String publisherId,
                                                @RequestBody @Valid PublisherUpdateRequestDTO requestDTO){
        publisherService.updatePublisher(publisherId, requestDTO);
        return ResponseEntity.ok().build();
    }
}

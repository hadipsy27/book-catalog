package com.labs.catalog.web;

import com.labs.catalog.annotation.LogThisMethod;
import com.labs.catalog.dto.PublisherCreateRequestDTO;
import com.labs.catalog.dto.PublisherListResponseDTO;
import com.labs.catalog.dto.PublisherUpdateRequestDTO;
import com.labs.catalog.dto.ResultPageResponseDTO;
import com.labs.catalog.exception.BadRequestException;
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

    @LogThisMethod
    @GetMapping("/v1/publisher")
    public ResponseEntity<ResultPageResponseDTO<PublisherListResponseDTO>> findPublisherList(
            @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "publisherName", required = false) String publisherName){

        if (pages<0) throw new BadRequestException("Invalid page number");
        return ResponseEntity.ok().body(publisherService.findPublisherList(pages, limit, sortBy, direction, publisherName));
    }
}

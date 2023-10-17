package com.labs.catalog.web;

import com.labs.catalog.dto.HelloMessageDTO;
import com.labs.catalog.service.GreetingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class HelloResource {

    Logger logger = LoggerFactory.getLogger(HelloResource.class);
    private GreetingService greetingService;

    public HelloResource(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello")
    public ResponseEntity<HelloMessageDTO> helloWorld(){
        logger.trace("This is Log TRACE");
        logger.debug("This is Log DEBUG");
        logger.info("This is Log INFO");
        logger.warn("This is Log WARN");
        logger.error("This is Log ERROR");
        HelloMessageDTO dto = new HelloMessageDTO();
        dto.setMessage(greetingService.sayGreetings());
        // return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        return ResponseEntity.ok(dto);
    }

}

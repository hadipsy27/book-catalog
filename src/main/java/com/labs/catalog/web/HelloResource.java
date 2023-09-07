package com.labs.catalog.web;

import com.labs.catalog.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    Logger logger = LoggerFactory.getLogger(HelloResource.class);
    private GreetingService greetingService;

    public HelloResource(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello")
    public String helloWorld(){
        logger.trace("This is Log TRACE");
        logger.debug("This is Log DEBUG");
        logger.info("This is Log INFO");
        logger.warn("This is Log WARN");
        logger.error("This is Log ERROR");
        return greetingService.sayGreetings();
    }
}

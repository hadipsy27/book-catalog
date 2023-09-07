package com.labs.catalog.web;

import com.labs.catalog.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    private GreetingService greetingService;

    public HelloResource(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello")
    public String helloWorld(){
        return greetingService.sayGreetings();
    }
}

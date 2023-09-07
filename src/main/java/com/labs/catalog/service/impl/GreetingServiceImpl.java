package com.labs.catalog.service.impl;

import com.labs.catalog.service.GreetingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.TimeZone;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Value("${welcome.text}")
    private String welcomeText;

    @Value("${timezone}")
    private String timeZone;

    @Value("${currency}")
    private String currency;
    @Override
    public String sayGreetings() {
        TimeZone timezone = TimeZone.getTimeZone(timeZone);
        return welcomeText + ", our timezone is " + timezone.getDisplayName() + ", our currency is " + currency;
    }
}

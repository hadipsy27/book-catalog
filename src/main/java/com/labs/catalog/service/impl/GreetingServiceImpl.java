package com.labs.catalog.service.impl;

import com.labs.catalog.config.ApplicationProperties;
import com.labs.catalog.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.TimeZone;

@Service
public class GreetingServiceImpl implements GreetingService {

    private ApplicationProperties applicationProperties;

    public GreetingServiceImpl(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public String sayGreetings() {
        TimeZone timezone = TimeZone.getTimeZone(applicationProperties.getTimezone());
        return applicationProperties.getWelcomeText() + ", our timezone is " + timezone.getDisplayName() + ", our currency is " + applicationProperties.getCurrency();
    }
}

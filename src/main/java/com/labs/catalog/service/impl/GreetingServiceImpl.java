package com.labs.catalog.service.impl;

import com.labs.catalog.config.ApplicationProperties;
import com.labs.catalog.config.CloudProperties;
import com.labs.catalog.service.GreetingService;
import org.springframework.stereotype.Service;

import java.util.TimeZone;

@Service
public class GreetingServiceImpl implements GreetingService {

    private ApplicationProperties applicationProperties;

    private CloudProperties cloudProperties;
    public GreetingServiceImpl(ApplicationProperties applicationProperties, CloudProperties cloudProperties) {
        super();
        this.applicationProperties = applicationProperties;
        this.cloudProperties = cloudProperties;
    }

    public String sayGreetings() {
        System.out.println(cloudProperties.getApiKey());
        System.out.println(cloudProperties.getApiSecretIde());
        TimeZone timezone = TimeZone.getTimeZone(applicationProperties.getTimezone());
        return applicationProperties.getWelcomeText() + ", our timezone is " + timezone.getDisplayName() + ", our currency is " + applicationProperties.getCurrency();
    }
}

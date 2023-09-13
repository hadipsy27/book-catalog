package com.labs.catalog.service;

import com.labs.catalog.dto.PublisherCreateRequestDTO;
import com.labs.catalog.dto.PublisherUpdateRequestDTO;

public interface PublisherService {

    public void createPublisher(PublisherCreateRequestDTO dto);

    public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto);
}

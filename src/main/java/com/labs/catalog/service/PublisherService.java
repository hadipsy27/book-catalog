package com.labs.catalog.service;

import com.labs.catalog.domain.Publisher;
import com.labs.catalog.dto.PublisherCreateRequestDTO;
import com.labs.catalog.dto.PublisherListResponseDTO;
import com.labs.catalog.dto.PublisherUpdateRequestDTO;
import com.labs.catalog.dto.ResultPageResponseDTO;

public interface PublisherService {

    public void createPublisher(PublisherCreateRequestDTO dto);

    Publisher findPublisher(String publisherId);

    public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto);

    public ResultPageResponseDTO<PublisherListResponseDTO> findPublisherList(Integer pages, Integer limit,
                                                                             String sortBy, String direction, String publisherName);
}

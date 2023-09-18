package com.labs.catalog.service;

import com.labs.catalog.domain.Publisher;
import com.labs.catalog.dto.*;

import java.util.List;

public interface PublisherService {

    public void createPublisher(PublisherCreateRequestDTO dto);

    Publisher findPublisher(String publisherId);

    public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto);

    public ResultPageResponseDTO<PublisherListResponseDTO> findPublisherList(Integer pages, Integer limit,
                                                                             String sortBy, String direction, String publisherName);

    PublisherReponseDTO constructDTO(Publisher publisher);
}

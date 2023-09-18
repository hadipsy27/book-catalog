package com.labs.catalog.service.impl;

import com.labs.catalog.domain.Publisher;
import com.labs.catalog.dto.PublisherCreateRequestDTO;
import com.labs.catalog.dto.PublisherListResponseDTO;
import com.labs.catalog.dto.PublisherUpdateRequestDTO;
import com.labs.catalog.dto.ResultPageResponseDTO;
import com.labs.catalog.exception.BadRequestException;
import com.labs.catalog.repository.PublisherRepository;
import com.labs.catalog.service.PublisherService;
import com.labs.catalog.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public void createPublisher(PublisherCreateRequestDTO dto) {
        Publisher publisher = new Publisher();
        publisher.setName(dto.getPublisherName());
        publisher.setCompanyName(dto.getCompanyName());
        publisher.setAddress(dto.getAddress());

        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto) {
        Publisher publisher = publisherRepository.findBySecureId(publisherId)
                .orElseThrow(() -> new BadRequestException("Invalid.publisher_id"));

        publisher.setName(dto.getPublisherName() == null || dto.getPublisherName().isBlank() ? publisher.getName() : dto.getPublisherName());
        publisher.setCompanyName(dto.getCompanyName() == null || dto.getCompanyName().isBlank() ? publisher.getCompanyName() : dto.getCompanyName());
        publisher.setAddress(dto.getAddress());

        publisherRepository.save(publisher);

    }

    @Override
    public ResultPageResponseDTO<PublisherListResponseDTO> findPublisherList(Integer pages,
                                                                             Integer limit, String sortBy,
                                                                             String direction, String publisherName) {

        publisherName = StringUtils.isBlank(publisherName) ? "%" : publisherName+"%"; // StringUtils from apache.commons.lang3
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);

        Page<Publisher> pageResult = publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);

        // parse page result to DTO
        List<PublisherListResponseDTO> dtos = pageResult.stream().map((p) -> {
            PublisherListResponseDTO dto = new PublisherListResponseDTO();

            dto.setPublisherId(p.getSecureId());
            dto.setPublisherName(p.getName());
            dto.setCompanyName(p.getCompanyName());

            return dto;
        }).collect(Collectors.toList());
        return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public Publisher findPublisher(String publisherId) {
        return publisherRepository.findBySecureId(publisherId)
                .orElseThrow(() -> new BadRequestException("Invalid.publisher_id"));
    }
}

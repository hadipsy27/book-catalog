package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BookDetailResponseDTO implements Serializable {

    private static final long serialVersionUID = -78872665539377L;

    private String bookId;
    private List<AuthorResponseDTO> authors;
    private List<CategoryListResponseDTO> categories;
    private PublisherReponseDTO publisher;
    private String bookTitle;
    private String bookDescription;
}

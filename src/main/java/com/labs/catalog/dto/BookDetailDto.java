package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BookDetailDto implements Serializable {

    private static final long serialVersionUID = -78872665539377L;
    private Long bookId;
    private String authorName;
    private String bookTitle;
    private String bookDescription;
}

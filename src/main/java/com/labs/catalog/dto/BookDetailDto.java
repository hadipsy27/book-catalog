package com.labs.catalog.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDetailDto implements Serializable {

    private static final long serialVersionUID = -78872665539377L;
    private Long bookId;
    private String authorName;
    private String bookTitle;
    private String bookDescription;
}

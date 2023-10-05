package com.labs.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class BookQueryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7954756059659172082L;
    private Long id;
    private String bookId;
    private String bookTitle;
    private String publisherName;
    private String description;
}

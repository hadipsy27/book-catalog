package com.labs.catalog.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {

    private static final long serialVersionUID = -1234567L;
    private Long id;
    private String title;
    private String description;
    private Author author;

}

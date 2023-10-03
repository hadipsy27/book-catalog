package com.labs.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthorQueryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3148884547383729981L;

    private Long bookId;

    private String authorName;
}

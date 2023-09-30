package com.labs.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Data
public class CategoryQueryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8770497465716650820L;

    private Long bookId;

    private String categoryCode;
}

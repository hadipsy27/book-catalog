package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryListResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1906383109043867173L;

    private String code;

    private String name;

    private String description;
}

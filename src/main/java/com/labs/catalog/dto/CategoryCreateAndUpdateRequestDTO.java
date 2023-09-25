package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryCreateAndUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3875916382055343939L;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;
}

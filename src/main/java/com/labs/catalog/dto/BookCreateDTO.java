package com.labs.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookCreateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4579125796259595001L;

    @NotBlank
    private String bookTitle;

    @NotEmpty
    private List<String> authorId;

    @NotEmpty
    private List<String> categoryList;

    @NotBlank
    private String publisherId;

    @JsonProperty("synopsis")
    private String description;
}

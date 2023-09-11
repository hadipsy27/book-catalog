package com.labs.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BookUpdateRequestDTO {
    @NotBlank
    private String bookTitle;

    @NotBlank
    private String authorName;

    @JsonProperty("synopsis")
    private String description;

}

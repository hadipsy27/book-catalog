package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AuthorCreateRequestDTO {

    @NotBlank
    private String authorName;

    @NotNull
    private Long birthDate;

    private List<AddressCreateRequestDTO> addresses;
}

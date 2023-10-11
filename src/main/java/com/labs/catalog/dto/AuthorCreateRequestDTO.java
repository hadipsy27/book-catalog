package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.labs.catalog.validator.annotation.ValidAuthorName;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AuthorCreateRequestDTO {

    @ValidAuthorName
    @NotBlank
    private String authorName;

    @NotNull
    private Long birthDate;

    @NotEmpty
    private List<AddressCreateRequestDTO> addresses;
}

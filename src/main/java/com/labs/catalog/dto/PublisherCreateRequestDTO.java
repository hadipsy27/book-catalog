package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.labs.catalog.annotation.LogThisArgs;
import javax.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@LogThisArgs
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6778192038252643291L;

    @NotBlank(message = "Publisher must not be blank")
    private String publisherName;

    @NotBlank(message = "Company name must not be blank")
    private String companyName;

    private String address;

}

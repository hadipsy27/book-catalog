package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -46431091150263659L;

    private String streetName;

    private String cityName;

    private String zipCode;
}

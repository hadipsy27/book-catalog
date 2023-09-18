package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherReponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7389630117996581933L;

    private String publisherId;

    private String publisherName;
}

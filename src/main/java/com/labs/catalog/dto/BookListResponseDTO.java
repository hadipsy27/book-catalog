package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookListResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -377222340958070667L;
    private String id;
    private String title;
    private String description;
    private String publisherName;
    private List<String> categoryCodes;
    private List<String> authorNames;
}

package com.labs.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CategoryCreateUpdateRecordDTO(@NotBlank String code, @NotBlank String name, @NotBlank String description) {
}

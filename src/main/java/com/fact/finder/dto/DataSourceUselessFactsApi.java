package com.fact.finder.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSourceUselessFactsApi(
        @JsonAlias("data")
        @NotBlank
        String body
) {
}

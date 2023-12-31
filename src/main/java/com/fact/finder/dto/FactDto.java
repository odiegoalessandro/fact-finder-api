package com.fact.finder.dto;

import jakarta.validation.constraints.NotBlank;

public record FactDto(@NotBlank String title, @NotBlank String body, @NotBlank String source) {
}

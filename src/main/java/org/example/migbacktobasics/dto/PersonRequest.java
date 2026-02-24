package org.example.migbacktobasics.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record PersonRequest(
    @NotBlank(message = "first name cannot be empty") String firstName,
    @NotBlank String lastName
) {
}

package org.example.migbacktobasics.dto;

import jakarta.validation.constraints.NotBlank;
import org.example.migbacktobasics.validation.NullOrNotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record PersonRequest(
    @NotBlank(message = "first name cannot be empty") String firstName,
    @NotBlank String lastName,
    @NullOrNotBlank String middleName
) {
}

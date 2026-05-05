package org.example.migbacktobasics.properties;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("mig.http")
public record HttpClientProperties(
    @NotBlank String url,
    @NotBlank String username,
    @NotBlank String password
) {
}

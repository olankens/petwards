package org.example.petwards.api.models.security.forms;

import jakarta.validation.constraints.NotBlank;

public record LoginForm(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}

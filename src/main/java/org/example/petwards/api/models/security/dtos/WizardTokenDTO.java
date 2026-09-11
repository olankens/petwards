package org.example.petwards.api.models.security.dtos;

public record WizardTokenDTO(
        WizardSessionDTO wizard,
        String token
) {
}

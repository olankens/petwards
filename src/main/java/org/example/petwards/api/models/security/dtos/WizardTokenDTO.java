package org.example.petwards.api.models.security.dtos;

public record WizardTokenDTO(
        UserSessionDTO user,
        String token
) {
}

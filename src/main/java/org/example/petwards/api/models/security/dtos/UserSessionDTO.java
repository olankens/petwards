package org.example.petwards.api.models.security.dtos;


import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;

public record UserSessionDTO(
        Long id,
        ShelterRole role,
        String fullName
) {

    public static UserSessionDTO fromUser(Wizard wizard) {
        String fullName = wizard.getLastName() + " " + wizard.getFirstName();
        return new UserSessionDTO(wizard.getId(),wizard.getShelterRole(),fullName);
    }
}

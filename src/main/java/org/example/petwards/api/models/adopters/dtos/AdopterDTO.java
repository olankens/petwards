package org.example.petwards.api.models.adopters.dtos;

import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;

public record AdopterDTO(
        Long id,
        String fullName,
        ShelterRole shelterRole
) {
    public static AdopterDTO fromWizardStaff(Wizard wizard) {
        String fullName = wizard.getLastName() + " " + wizard.getFirstName();
        return new AdopterDTO(wizard.getId(), fullName, wizard.getShelterRole());
    }
}

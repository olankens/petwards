package org.example.petwards.api.models.security.dtos;


import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;

public record WizardSessionDTO(
        Long id,
        ShelterRole role,
        String fullName
) {

    public static WizardSessionDTO fromWizard(Wizard wizard) {
        String fullName = wizard.getLastName() + " " + wizard.getFirstName();
        return new WizardSessionDTO(wizard.getId(), wizard.getShelterRole(), fullName);
    }
}

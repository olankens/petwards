package org.example.petwards.api.models.adopters.dtos;

import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.WizardHouse;

public record AdopterDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        WizardHouse wizardHouse
) {
    public static AdopterDTO fromAdopter(Wizard wizard) {
        return new AdopterDTO(
                wizard.getId(),
                wizard.getFirstName(),
                wizard.getLastName(),
                wizard.getEmail(),
                wizard.getWizardHouse()
        );
    }
}

package org.example.petwards.api.models.wizards.dtos;

import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.WizardHouse;

public record WizardAdoptionDTO(
        Long id,
        String fullName,
        WizardHouse wizardHouse
) {
    public static WizardAdoptionDTO fromWizardAdoption(Wizard wizard) {
        String fullName = wizard.getLastName() + " " + wizard.getFirstName();
        return new WizardAdoptionDTO(wizard.getId(), fullName, wizard.getHouse());
    }
}

package be.bstorm.petwards.api.models.wizards.dtos;

import be.bstorm.petwards.dl.entities.Wizard;
import be.bstorm.petwards.dl.enums.WizardHouse;

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

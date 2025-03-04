package be.bstorm.petwards.api.models.wizards.dtos;

import be.bstorm.petwards.dl.entities.Wizard;

public record WizardDTO(
        Long id,
        String fullName
) {
    public static WizardDTO fromWizard(Wizard wizard) {
        String fullName = wizard.getLastName() + " " + wizard.getFirstName();
        return new WizardDTO(wizard.getId(), fullName);
    }
}

package be.bstorm.petwards.api.models.wizards.dtos;

import be.bstorm.petwards.dl.entities.Wizard;
import be.bstorm.petwards.dl.enums.ShelterRole;

public record WizardStaffDTO(
        Long id,
        String fullName,
        ShelterRole shelterRole
) {
    public static WizardStaffDTO fromWizardStaff(Wizard wizard) {
        String fullName = wizard.getLastName() + " " + wizard.getFirstName();
        return new WizardStaffDTO(wizard.getId(), fullName, wizard.getShelterRole());
    }
}

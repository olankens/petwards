package org.example.petwards.api.models.staffs.dtos;

import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;
import org.example.petwards.dl.enums.WizardHouse;

public record StaffDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        ShelterRole shelterRole,
        WizardHouse wizardHouse
) {


    public static StaffDTO fromWizardStaff(Wizard wizard) {
        return new StaffDTO(wizard.getId(), wizard.getFirstName(), wizard.getLastName(), wizard.getEmail(),wizard.getShelterRole(), wizard.getWizardHouse());
    }
}

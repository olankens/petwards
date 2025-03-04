package org.example.petwards.api.models.staffs.dtos;

import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;

public record StaffDTO(
        Long id,
        String fullName,
        ShelterRole shelterRole
) {
    public static StaffDTO fromWizardStaff(Wizard wizard) {
        String fullName = wizard.getLastName() + " " + wizard.getFirstName();
        return new StaffDTO(wizard.getId(), fullName, wizard.getShelterRole());
    }
}

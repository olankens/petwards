package org.example.petwards.api.models.staffs.forms;

import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.WizardHouse;

public record StaffForm(
       String  firstName,
       String  lastName,
       String email,
       String password,
       WizardHouse wizardHouse
) {
    public Wizard toWizard  () {
        return new Wizard(this.firstName,this.lastName, this.email, this.password, this.wizardHouse);
    }
}

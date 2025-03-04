package org.example.petwards.api.models.security.forms;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;
import org.example.petwards.dl.enums.WizardHouse;

import java.time.LocalDate;

public record RegisterForm(
        @NotBlank @Size(max = 150)
        String email,
        @NotBlank
        String password,
        @NotBlank @Size(max = 123)
        String firstName,
        @NotBlank @Size(max = 80)
        String lastName,
        @NotBlank
        WizardHouse wizardHouse
) {

    public Wizard toWizard() {
        return new Wizard(
                this.firstName,
                this.lastName,
                this.email,
                this.wizardHouse,
                this.password



        );
    }
}

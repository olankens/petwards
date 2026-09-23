package org.example.petwards.api.models.adoptions.forms;

import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.AdoptionStatus;

public record AdoptionForm(
        AdoptionStatus adoptionStatus,
        Beast beast,
        Wizard wizard
) {
    public Adoption toAdoption() {
        return new Adoption(this.adoptionStatus, this.beast, this.wizard);
    }

}

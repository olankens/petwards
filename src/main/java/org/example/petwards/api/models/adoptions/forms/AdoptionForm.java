package org.example.petwards.api.models.adoptions.forms;

import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.entities.Wizard;

public record AdoptionForm(
        String adoptionStatus,
        Beast beast,
        Wizard wizard
) {
    public Adoption toAdoption() {
        return new Adoption(this.adoptionStatus, this.beast, this.wizard);
    }

}

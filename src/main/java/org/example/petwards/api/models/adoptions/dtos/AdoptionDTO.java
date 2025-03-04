package org.example.petwards.api.models.adoptions.dtos;

import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.enums.AdoptionStatus;

public record AdoptionDTO(
        Long id,
        AdoptionStatus adoptionStatus
) {
    public static AdoptionDTO fromAdoption(Adoption adoption){
        return new AdoptionDTO(adoption.getId(), adoption.getStatus());
    }
}

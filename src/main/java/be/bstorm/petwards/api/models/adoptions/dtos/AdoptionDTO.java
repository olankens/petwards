package be.bstorm.petwards.api.models.adoptions.dtos;

import be.bstorm.petwards.dl.entities.Adoption;
import be.bstorm.petwards.dl.enums.AdoptionStatus;

public record AdoptionDTO(
        Long id,
        AdoptionStatus adoptionStatus
) {
    public static AdoptionDTO fromAdoption(Adoption adoption){
        return new AdoptionDTO(adoption.getId(), adoption.getStatus());
    }
}

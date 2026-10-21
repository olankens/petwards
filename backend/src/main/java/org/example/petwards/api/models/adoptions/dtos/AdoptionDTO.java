package org.example.petwards.api.models.adoptions.dtos;

import org.example.petwards.api.models.beasts.dtos.BeastDTO;
import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.enums.AdoptionStatus;

public record AdoptionDTO(
        Long id,
        AdoptionStatus adoptionStatus,
        BeastDTO beastDTO
) {
    public static AdoptionDTO fromAdoption(Adoption adoption){
        BeastDTO beastDTO = BeastDTO.fromBeast(adoption.getBeast());
        return new AdoptionDTO(adoption.getId(), adoption.getStatus(), beastDTO);
    }
}

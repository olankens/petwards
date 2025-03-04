package org.example.petwards.api.models.shelters.dtos;

import org.example.petwards.dl.entities.Shelter;

public record ShelterDTO(
        Long id,
        String name
) {
    public static ShelterDTO fromShelter(Shelter shelter){
        return new ShelterDTO(shelter.getId(), shelter.getName());
    }
}

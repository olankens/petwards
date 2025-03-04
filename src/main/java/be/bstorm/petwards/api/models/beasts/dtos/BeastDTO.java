package be.bstorm.petwards.api.models.beasts.dtos;

import be.bstorm.petwards.dl.entities.Beast;

public record BeastDTO(
        Long id,
        String name,
        Boolean isAvailable
) {
    public static BeastDTO fromBeast(Beast beast){
        return new BeastDTO(beast.getId(), beast.getName(), beast.isAvailable());
    }
}

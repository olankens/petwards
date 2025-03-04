package be.bstorm.petwards.api.models.capabilities.dtos;

import be.bstorm.petwards.dl.entities.Capability;

public record CapabilityDTO(
        Long id,
        String name
) {
    public static CapabilityDTO fromCapability(Capability capability){
        return new CapabilityDTO(capability.getId(), capability.getName());
    }
}

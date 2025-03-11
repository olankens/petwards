package org.example.petwards.api.models.beasts.dtos;

import org.example.petwards.api.models.capabilities.dtos.CapabilityDTO;
import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.entities.Capability;

import java.util.Set;
import java.util.stream.Collectors;

public record BeastDTO(
        Long id,
        String name,
        Boolean isAvailable,
        Set<CapabilityDTO> capabilities
) {
    public static BeastDTO fromBeast(Beast beast){
        return new BeastDTO(
                beast.getId(),
                beast.getName(),
                beast.isAvailable(),
                beast.getCapabilities().stream().map(CapabilityDTO::fromCapability).collect(Collectors.toSet())
        );
    }
}

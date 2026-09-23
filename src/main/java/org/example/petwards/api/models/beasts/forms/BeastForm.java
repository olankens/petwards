package org.example.petwards.api.models.beasts.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.petwards.api.models.adoptions.dtos.AdoptionDTO;
import org.example.petwards.api.models.capabilities.dtos.CapabilityDTO;
import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.entities.Capability;
import org.example.petwards.dl.enums.DangerLevel;

import java.util.List;
import java.util.Set;

public record BeastForm(
        @NotBlank @Size(max = 100)
        String name,
        Boolean isAvailable,
        DangerLevel dangerLevel,
        List<String> capabilities
) {
        public Beast toBeast() {
                return new Beast(
                        name,
                        isAvailable,
                        dangerLevel
                );
        }
}

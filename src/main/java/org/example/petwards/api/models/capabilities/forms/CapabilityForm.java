package org.example.petwards.api.models.capabilities.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.petwards.dl.entities.Capability;
import org.springframework.web.bind.annotation.RequestBody;

public record CapabilityForm (
        @NotBlank @Size(max = 100)
        String name
){
    public Capability toCapability() {
        return new Capability(this.name);
    }
}

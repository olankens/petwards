package org.example.petwards.api.models.beasts.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.enums.DangerLevel;

public record BeastForm(
        @NotBlank @Size(max= 100)
        String name,
        Boolean isAvailable,
        DangerLevel dangerLevel
) {
    public Beast toBeast(){
        return new Beast(this.name,this.isAvailable, this.dangerLevel);
    }
}

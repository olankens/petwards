package org.example.petwards.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shelter")
public class ShelterController {

    private final ShelterService shelterService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ShelterDTO> updateShelter(
            @RequestBody ShelterDTO shelterDTO
    ) {
        // INFO: We didn't need any id parameters as there is only one shelter
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

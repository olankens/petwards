package org.example.petwards.api.controllers;

import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.adoptions.dtos.AdoptionDTO;
import org.example.petwards.bll.AdoptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/adoption")
public class AdoptionController {

    private final AdoptionService adoptionService;

    @GetMapping("/pending")
    public ResponseEntity<CustomPage<AdoptionDTO>> getPendingAdoptions(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @GetMapping("/approved")
    public ResponseEntity<CustomPage<AdoptionDTO>> getApprovedAdoptions(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<AdoptionDTO> approveAdoption(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<AdoptionDTO> rejectAdoption(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

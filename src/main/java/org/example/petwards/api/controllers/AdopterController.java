package org.example.petwards.api.controllers;

import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.adopters.dtos.AdopterDTO;
import org.example.petwards.bll.AdopterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/adopter")
public class AdopterController {


    private final AdopterService adopterService;

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @GetMapping
    public ResponseEntity<CustomPage<AdopterDTO>> getAllAdopters(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ADOPTER', 'STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<AdopterDTO> updateAdopter(
            @PathVariable Long id,
            @RequestBody AdopterDTO staffDTO
    ) {
        // INFO: If ADOPTER, check if it is the current Wizard
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdopter(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

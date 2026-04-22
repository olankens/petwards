package org.example.petwards.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.adoptions.dtos.AdoptionDTO;
import org.example.petwards.bll.AdoptionService;
import org.example.petwards.bll.exceptions.PetwardsAdoptionNotFoundException;
import org.example.petwards.dl.entities.Adoption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/adoption")
public class AdoptionController {

    private final AdoptionService adoptionService;

    @Operation(summary = "Returns all adoptions with pending status")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('STAFF')")
    @GetMapping("/pending")
    public ResponseEntity<CustomPage<AdoptionDTO>> getPendingAdoptions(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size

    ) {
        List<Adoption> pendingAdoptions = adoptionService.getPendingAdoption();
        List<AdoptionDTO> adoptionDTOs = pendingAdoptions.stream()
                .map(AdoptionDTO::fromAdoption)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new CustomPage<>(adoptionDTOs, page, size));
    }

    @Operation(summary = "Returns all adoptions with approved status")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('STAFF')")
    @GetMapping("/approved")
    public ResponseEntity<CustomPage<AdoptionDTO>> getApprovedAdoptions(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        List<Adoption> approvedAdoptions = adoptionService.getApproveAdoption();
        List<AdoptionDTO> adoptionDTOs = approvedAdoptions.stream()
                .map(AdoptionDTO::fromAdoption)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new CustomPage<>(adoptionDTOs, page, size));
    }

    @Operation(summary = "Approves an adoption")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('STAFF')")
    @PutMapping("/approve/{id}")
    public ResponseEntity<AdoptionDTO> approveAdoption(
            @PathVariable Long id
    ) {
        try {
            Adoption adoption = adoptionService.approveAdoption(id);
            return new ResponseEntity<>(AdoptionDTO.fromAdoption(adoption), HttpStatus.OK);
        } catch (PetwardsAdoptionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Rejects an adoption")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('STAFF')")
    @PutMapping("/reject/{id}")
    public ResponseEntity<AdoptionDTO> rejectAdoption(
            @PathVariable Long id
    ) {
        try {
            Adoption adoption = adoptionService.rejectAdoption(id);
            return new ResponseEntity<>(AdoptionDTO.fromAdoption(adoption), HttpStatus.OK);
        } catch (PetwardsAdoptionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package org.example.petwards.api.controllers;

import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.adoptions.dtos.AdoptionDTO;
import org.example.petwards.bll.AdoptionService;
import org.example.petwards.bll.exceptions.AdoptionNotFoundException;
import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.enums.AdoptionStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Adoption> pendingAdoptions = adoptionService.getPendingAdoption();
        List<AdoptionDTO> adoptionDTOs = pendingAdoptions.stream()
                .map(AdoptionDTO::fromAdoption)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new CustomPage<>(adoptionDTOs, page, size));
    }


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

    @PutMapping("/approve/{id}")
    public ResponseEntity<AdoptionDTO> approveAdoption(
            @PathVariable Long id
    ) {
        try{
            Adoption adoption = adoptionService.findById(id);
            adoption.setStatus(AdoptionStatus.APPROVED);
            return new ResponseEntity<>(AdoptionDTO.fromAdoption(adoption), HttpStatus.OK);
        }catch (AdoptionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<AdoptionDTO> rejectAdoption(
            @PathVariable Long id
    ) {
        try{
            Adoption adoption = adoptionService.findById(id);
            adoption.setStatus(AdoptionStatus.REJECTED);
            return new ResponseEntity<>(AdoptionDTO.fromAdoption(adoption), HttpStatus.OK);
        }catch (AdoptionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

package org.example.petwards.api.controllers;

import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.shelters.dtos.ShelterDTO;
import org.example.petwards.bll.ShelterService;
import org.example.petwards.bll.exceptions.PetwardsShelterNotFoundException;
import org.example.petwards.dl.entities.Shelter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shelter")
public class ShelterController {

    private final ShelterService shelterService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @GetMapping
    public ResponseEntity<List<ShelterDTO>> getAllShelters() {
        List<Shelter> shelters = shelterService.findAll();
        List<ShelterDTO> shelterDTOs = shelters.stream()
                .map(ShelterDTO::fromShelter)
                .toList();
        return new ResponseEntity<>(shelterDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<ShelterDTO> getShelterById(@PathVariable Long id) {
        try {
            Shelter shelter = shelterService.findById(id);
            return new ResponseEntity<>(ShelterDTO.fromShelter(shelter), HttpStatus.OK);
        } catch (PetwardsShelterNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ShelterDTO> updateShelter(
            @PathVariable Long id,
            @RequestBody ShelterDTO shelterDTO
    ) {
        // INFO: We didn't need any id parameters as there is only one shelter
        try {
            Shelter shelter = new Shelter(shelterDTO.name(), shelterDTO.description());
            shelterService.update(id, shelter);
            return new ResponseEntity<>(ShelterDTO.fromShelter(shelter), HttpStatus.OK);
        } catch (PetwardsShelterNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
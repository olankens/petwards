package org.example.petwards.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.beasts.dtos.BeastDTO;
import org.example.petwards.api.models.beasts.forms.BeastForm;
import org.example.petwards.bll.BeastService;
import org.example.petwards.bll.CapabilityService;
import org.example.petwards.bll.exceptions.PetwardsBeastNotFoundException;
import org.example.petwards.dal.repositories.CapabilityRepository;
import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.entities.Capability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beast")
public class BeastController {

    private final BeastService beastService;
    private final CapabilityService capabilityService;

    @Operation(summary = "Returns all beasts with or without name and/or capability")
    @GetMapping
    public ResponseEntity<CustomPage<BeastDTO>> getBeastsByNameAndCapabilities(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> capabilities
    ) {
        Page<Beast> beasts = beastService.findAllByNameAndCapability(PageRequest.of(
                page - 1, size, Sort.by(Sort.Direction.ASC, "id")
        ), name, capabilities);
        List<BeastDTO> beastsDTOs = beasts.getContent().stream()
                .map(BeastDTO::fromBeast)
                .collect(Collectors.toList());
        CustomPage<BeastDTO> result = new CustomPage<>(beastsDTOs, beasts.getTotalPages(), beasts.getNumber() + 1);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Returns a beast with specified id (if exists)")
    @GetMapping("/{id}")
    public ResponseEntity<BeastDTO> getBeastById(@PathVariable Long id) {
        try {
            Beast beast = beastService.findById(id);
            return new ResponseEntity<>(BeastDTO.fromBeast(beast), HttpStatus.OK);
        } catch (PetwardsBeastNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Creates a new beast")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @PostMapping
    public ResponseEntity<BeastDTO> createBeast(
            @RequestBody BeastForm form
    ) {
        Beast beast = form.toBeast();
        List<String> capabilities = form.capabilities();

        capabilities.forEach(capability -> {
           Capability capabilityToAdd = capabilityService.findOrCreateCapabilityByName(capability);
           beast.getCapabilities().add(capabilityToAdd);
            // TODO: Create the capability if doesn't exist
        });

        beastService.createBeast(beast);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Updates a beast with specified id (if exists)")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<BeastDTO> updateBeast(
            @PathVariable Long id,
            @Valid @RequestBody BeastForm form
    ) {

        Beast beast = form.toBeast();
        beastService.update(id, beast);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a beast with specified id (if exists)")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeast(@PathVariable Long id) {
        beastService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

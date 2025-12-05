package org.example.petwards.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.capabilities.dtos.CapabilityDTO;
import org.example.petwards.api.models.capabilities.forms.CapabilityForm;
import org.example.petwards.bll.CapabilityService;
import org.example.petwards.bll.exceptions.PetwardsShelterNotFoundException;
import org.example.petwards.dl.entities.Capability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/capability")
public class CapabilityController {

    private final CapabilityService capabilityService;

    @Operation(summary = "Returns all capabilities")
    @GetMapping
    public ResponseEntity<CustomPage<CapabilityDTO>> getAllCapabilities(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ) {
        Page<Capability> capabilities = capabilityService.findAll(PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "id")));
        List<CapabilityDTO> capabilitiesDTOs = capabilities.getContent().stream()
                .map(CapabilityDTO::fromCapability)
                .toList();
        CustomPage<CapabilityDTO> result = new CustomPage<>(capabilitiesDTOs, capabilities.getTotalPages(), capabilities.getNumber() + 1);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Returns a capability with specified id")
    @GetMapping("/{id}")
    public ResponseEntity<CapabilityDTO> getCapabilityById(@PathVariable Long id) {
        try {
            Capability capability = capabilityService.findById(id);
            return new ResponseEntity<>(CapabilityDTO.fromCapability(capability), HttpStatus.OK);
        } catch (PetwardsShelterNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Creates a new capability")
    @PostMapping
    public ResponseEntity<CapabilityDTO> createCapability(
            @Valid @RequestBody CapabilityForm capabilityform
    ) {
        Capability capability = capabilityform.toCapability();
        capabilityService.createCapability(capability);
        return ResponseEntity.noContent().build();
    }


}

package org.example.petwards.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.capabilities.dtos.CapabilityDTO;
import org.example.petwards.api.models.capabilities.forms.CapabilityForm;
import org.example.petwards.api.models.shelters.dtos.ShelterDTO;
import org.example.petwards.bll.CapabilityService;
import org.example.petwards.bll.exceptions.ShelterNotFoundException;
import org.example.petwards.dl.entities.Capability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/capability")
public class CapabilityController {

    private final CapabilityService capabilityService;

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

    @GetMapping("/{id}")
    public ResponseEntity<CapabilityDTO> getCapabilityById(@PathVariable Long id) {
        try {
            Capability capability = capabilityService.findById(id);
            return new ResponseEntity<>(CapabilityDTO.fromCapability(capability), HttpStatus.OK);
        } catch (ShelterNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<CapabilityDTO> createCapability(
            @Valid @RequestBody CapabilityForm capabilityform
    ) {
        Capability capability = capabilityform.toCapability();
        capabilityService.createCapability(capability);
        return ResponseEntity.noContent().build();

//        Long id = capabilityService.save(capability).getId();
//        UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id);
//        return ResponseEntity.created(uriComponents.toUri()).build();
    }


}

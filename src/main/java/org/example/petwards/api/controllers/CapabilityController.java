package org.example.petwards.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.capabilities.dtos.CapabilityDTO;
import org.example.petwards.api.models.capabilities.forms.CapabilityForm;
import org.example.petwards.bll.CapabilityService;
import org.example.petwards.dl.entities.Capability;
import org.example.petwards.dl.entities.Wizard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        ResponseEntity<CustomPage<CapabilityDTO>> responseResult = ResponseEntity.ok(result);
        return responseResult;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Void> createCapability(
            @Valid @RequestBody CapabilityForm form
    ) {
        Capability capability = form.toCapability();
        Long id = capabilityService.save(capability).getId();
        UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }


}

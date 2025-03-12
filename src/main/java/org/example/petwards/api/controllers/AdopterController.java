package org.example.petwards.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.adopters.dtos.AdopterDTO;
import org.example.petwards.api.models.adopters.forms.AdopterForm;
import org.example.petwards.bll.AdopterService;
import org.example.petwards.dl.entities.Wizard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/adopter")
public class AdopterController {

    private final AdopterService adopterService;

    @Operation(summary = "Returns all wizards with adopter role")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @GetMapping
    public ResponseEntity<CustomPage<AdopterDTO>> getAllAdopters(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Page<Wizard> adopters = adopterService.getAllAdopters(
                PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "id"))
        );
        List<AdopterDTO> dtos = adopters.getContent().stream()
                .map(AdopterDTO::fromAdopter)
                .toList();
        CustomPage<AdopterDTO> results = new CustomPage<>(dtos, adopters.getTotalPages(), adopters.getNumber() + 1);
        return ResponseEntity.ok(results);
    }

    @Operation(summary = "Returns a single wizard with adopter role")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ADOPTER', 'STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<AdopterDTO> getById(
            @PathVariable Long id,
            @AuthenticationPrincipal Wizard current
    ) {
        if (current != null && current.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADOPTER"))) {
            if (!current.getId().equals(id)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only access your own profile.");
            }
        }
        Wizard adopter = adopterService.findById(id);
        AdopterDTO dto = AdopterDTO.fromAdopter(adopter);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Updates the wizard with adopter role")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ADOPTER', 'STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<AdopterDTO> updateAdopter(
            @PathVariable Long id,
            @Valid @RequestBody AdopterForm form,
            @AuthenticationPrincipal Wizard current
    ) {
        if (current != null && current.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADOPTER"))) {
            if (!current.getId().equals(id)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only modify your own profile.");
            }
        }
        adopterService.updateAdopter(id, form.toWizard());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes the wizard with adopter role")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ADOPTER', 'STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdopter(
            @PathVariable Long id,
            @AuthenticationPrincipal Wizard current
    ) {
        if (current != null && current.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADOPTER"))) {
            if (!current.getId().equals(id)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only modify your own profile.");
            }
        }
        adopterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Adopts a beast")
    @PreAuthorize("hasAnyAuthority('ADOPTER')")
    @PostMapping("/adopt/{beastId}")
    public ResponseEntity<Void> adoptBeast(
            @PathVariable Long beastId,
            @AuthenticationPrincipal Wizard current
    ) {
        adopterService.adoptBeast(beastId, current);
        return ResponseEntity.noContent().build();
    }
}

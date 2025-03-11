package org.example.petwards.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.beasts.dtos.BeastDTO;
import org.example.petwards.api.models.beasts.forms.BeastForm;
import org.example.petwards.bll.BeastService;
import org.example.petwards.bll.exceptions.PetwardsBeastNotFoundException;
import org.example.petwards.dl.entities.Beast;
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


    @GetMapping
    public ResponseEntity<CustomPage<BeastDTO>> getBeastsByNameAndCapabilities(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> capabilities
            // TODO: Add filter by name
            // TODO: Add filter by capability list
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

    @GetMapping("/{id}")
    public ResponseEntity<BeastDTO> getBeastById(@PathVariable Long id) {
        try {
            Beast beast = beastService.findById(id);
            return new ResponseEntity<>(BeastDTO.fromBeast(beast), HttpStatus.OK);
        } catch ( PetwardsBeastNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @PostMapping
    public ResponseEntity<BeastDTO> createBeast(
            @Valid @RequestBody BeastForm beastForm
    ) {
        Beast beast = beastForm.toBeast();
        beastService.createBeast(beast);
        return ResponseEntity.noContent().build();

    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<BeastDTO> updateBeast(
            @PathVariable Long id,
            @RequestBody @Valid BeastForm beastForm
    ) {
        Beast beast = beastForm.toBeast();
        beastService.updateBeast(id, beast);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeast(
            @PathVariable Long id
    ) {
        beastService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

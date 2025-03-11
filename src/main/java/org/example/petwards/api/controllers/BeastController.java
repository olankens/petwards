package org.example.petwards.api.controllers;

import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.beasts.dtos.BeastDTO;
import org.example.petwards.api.models.capabilities.dtos.CapabilityDTO;
import org.example.petwards.bll.BeastService;
import org.example.petwards.bll.exceptions.ShelterNotFoundException;
import org.example.petwards.dal.repositories.BeastRepository;
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
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beast")
public class BeastController {

    private final BeastRepository beastRepository;
    private final BeastService beastService;

//    @GetMapping
//    public ResponseEntity<CustomPage<BeastDTO>> getAllBeast(
//            @RequestParam(required = false, defaultValue = "1") int page,
//            @RequestParam(required = false, defaultValue = "5") int size
//            // TODO: Add filter by name
//            // TODO: Add filter by capability list
//    ) {
//        Page<Beast> beasts = beastService.findAll(PageRequest.of(
//                page - 1, size, Sort.by(Sort.Direction.ASC, "id")
//        ));
//        List<BeastDTO> beastsDTOs = beasts.getContent().stream()
//                .map(BeastDTO::fromBeast)
//                .toList();
//        CustomPage<BeastDTO> result = new CustomPage<>(beastsDTOs, beasts.getTotalPages(), beasts.getNumber() + 1);
//        ResponseEntity<CustomPage<BeastDTO>> responseResult = ResponseEntity.ok(result);
//        return responseResult;
//    }

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
        } catch (ShelterNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('STAFF')")
    @PostMapping
    public ResponseEntity<BeastDTO> createBeast(
            @RequestBody BeastDTO beastDTO
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<BeastDTO> updateBeast(
            @PathVariable Long id,
            @RequestBody BeastDTO beastDTO
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeast(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

package org.example.petwards.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beast")
public class BeastController {

    private final BeastRepository beastRepository;

    @GetMapping
    public ResponseEntity<CustomPage<BeastDTO>> getAllBeast(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
            // TODO: Add filter by name
            // TODO: Add filter by capability list
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // TODO: Get beast

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @PostMapping
    public ResponseEntity<BeastDTO> createBeast(
            @RequestBody BeastDTO beastDTO
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BeastDTO> updateBeast(
            @PathVariable Long id,
            @RequestBody BeastDTO beastDTO
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeast(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

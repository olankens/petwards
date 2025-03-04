package org.example.petwards.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/capability")
public class CapabilityController {

    private final CapabilityService capabilityService;

    @GetMapping
    public ResponseEntity<CapabilityDTO> getAllCapabilities(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

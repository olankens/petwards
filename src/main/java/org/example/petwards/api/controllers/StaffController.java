package org.example.petwards.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.staffs.dtos.StaffDTO;
import org.example.petwards.api.models.staffs.from.StaffForm;
import org.example.petwards.bll.StaffService;
import org.example.petwards.bll.exceptions.PetwardsStaffNotFoundException;
import org.example.petwards.dl.entities.Wizard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @GetMapping
    public ResponseEntity<CustomPage<StaffDTO>> getAllStaffs(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size


    ) {
        List<Wizard> wizards = staffService.findAll();
        List<StaffDTO> staffDTOs = wizards.stream()
                .map(StaffDTO::fromWizardStaff)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new CustomPage<>(staffDTOs, page, size));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> findById(
            @PathVariable long id

    ) {
        try {
            Wizard wizard = staffService.findById(id);
            return new ResponseEntity<>(StaffDTO.fromWizardStaff(wizard), HttpStatus.OK);
        } catch (PetwardsStaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @PostMapping
    public ResponseEntity<StaffDTO> createStaff(
            @RequestBody @Valid StaffForm staffForm
    ) {
        Wizard wizard = staffForm.toWizard();
        staffService.createStaff(wizard);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> updateStaff(
            @PathVariable Long id,
            @RequestBody @Valid StaffForm staffForm
    ) {
        Wizard wizard = staffForm.toWizard();
        staffService.updateStaff(id, wizard);
        return ResponseEntity.noContent().build();
    }

}

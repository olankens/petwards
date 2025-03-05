package org.example.petwards.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.CustomPage;
import org.example.petwards.api.models.shelters.dtos.ShelterDTO;
import org.example.petwards.api.models.staffs.dtos.StaffDTO;
import org.example.petwards.api.models.staffs.from.StaffForm;
import org.example.petwards.bll.StaffService;
import org.example.petwards.bll.exceptions.ShelterNotFoundException;
import org.example.petwards.bll.exceptions.StaffNotFoundException;
import org.example.petwards.dl.entities.Shelter;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
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
//        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> findById(
            @PathVariable long id
    ){
        try{
            Wizard wizard = staffService.findById(id);
            return new ResponseEntity<>(StaffDTO.fromWizardStaff(wizard), HttpStatus.OK);
        }catch (StaffNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

        @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @PostMapping
    public ResponseEntity<StaffDTO> createStaff(
            @RequestBody @Valid StaffForm staffForm
    ) {

        Wizard wizard = staffForm.toWizard();
        staffService.createStaff(wizard);
        return ResponseEntity.noContent().build();

    }

        @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
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

package org.example.petwards.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petwards.api.models.security.dtos.WizardSessionDTO;
import org.example.petwards.api.models.security.dtos.WizardTokenDTO;
import org.example.petwards.api.models.security.forms.LoginForm;
import org.example.petwards.api.models.security.forms.RegisterForm;
import org.example.petwards.bll.AuthService;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.il.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "Registers a new wizard with adopter role")
    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody() RegisterForm form
    ) {
        authService.register(form.toWizard());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Logins any already registered wizard")
    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<WizardTokenDTO> login(
            @Valid @RequestBody LoginForm form
    ) {
        Wizard wizard = authService.login(form.email(), form.password());
        WizardSessionDTO sessionDTO = WizardSessionDTO.fromWizard(wizard);
        String token = jwtUtil.generateToken(wizard);
        WizardTokenDTO wizardTokenDTO = new WizardTokenDTO(sessionDTO, token);
        return ResponseEntity.ok(wizardTokenDTO);
    }
}


package org.example.petwards.api.controllers.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petwards.il.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestPart("form") RegisterForm form
    ) {
        // INFO: Only adopters can be registered
        // as staff can only be created by admin and other staffs
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<WizardTokenDTO> login(
            @Valid @RequestBody LoginForm form
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}


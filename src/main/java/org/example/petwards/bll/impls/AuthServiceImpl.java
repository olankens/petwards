package org.example.petwards.bll.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.AuthService;
import org.example.petwards.dal.repositories.WizardRepository;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final WizardRepository wizardRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void register(Wizard wizard) {
        if (wizardRepository.existsById(wizard.getId())) {
            throw new RuntimeException();
        }
        wizard.setPassword(passwordEncoder.encode(wizard.getPassword()));
        wizard.setShelterRole(ShelterRole.STAFF);
        wizardRepository.save(wizard);
    }

    @Override
    public Wizard login(Long id, String password) {
        Wizard user = wizardRepository.findById(id).orElseThrow(
                RuntimeException::new
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        return user;
    }

}

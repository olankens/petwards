package org.example.petwards.bll.impls;

import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.AuthService;
import org.example.petwards.dal.repositories.WizardRepository;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final WizardRepository wizardRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(Wizard wizard) {
        if (wizardRepository.existsByEmail(wizard.getEmail())) {

            throw new RuntimeException();
        }
        wizard.setPassword(passwordEncoder.encode(wizard.getPassword()));
        wizard.setShelterRole(ShelterRole.ADOPTER);
        wizardRepository.save(wizard);
    }

    @Override
    public Wizard login(String email, String password) {
        Wizard wizard = wizardRepository.findByEmail(email).orElseThrow(

                () -> new RuntimeException("")
        );
        if (!passwordEncoder.matches(password, wizard.getPassword())) {

            throw new RuntimeException();
        }
        return wizard;
    }
}

package org.example.petwards.bll.impls;

import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.AdopterService;
import org.example.petwards.dal.repositories.WizardRepository;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdopterServiceImpl implements AdopterService {

    private final WizardRepository wizardRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<Wizard> getAllAdopters(Pageable pageable) {
        return wizardRepository.findAllByShelterRole(ShelterRole.ADOPTER, pageable);
    }

    @Override
    public Wizard findById(Long id) {
        return wizardRepository.findByShelterRoleAndId(ShelterRole.ADOPTER, id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
    }

    @Override
    public Wizard save(Wizard wizardAdopter) {
        if (wizardRepository.existsById(wizardAdopter.getId())) {
            throw new RuntimeException("id already exists");
        }
        wizardRepository.save(wizardAdopter);
        return wizardAdopter;
    }

    @Override
    public void updateAdopter(Long id, Wizard wizardAdopter) {
        Wizard found = wizardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
        found.setFirstName(wizardAdopter.getFirstName());
        found.setLastName(wizardAdopter.getLastName());
        found.setEmail(wizardAdopter.getEmail());
        found.setPassword(passwordEncoder.encode(wizardAdopter.getPassword()));
        found.setWizardHouse(wizardAdopter.getWizardHouse());
        wizardRepository.save(found);
    }

    @Override
    public void deleteById(Long id) {
        if (!wizardRepository.existsById(id)) {
            throw new RuntimeException("id not found");
        }
        wizardRepository.deleteById(id);
    }
}

package org.example.petwards.bll.impls;

import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.AdopterService;
import org.example.petwards.dal.repositories.WizardRepository;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdopterServiceImpl implements AdopterService {

    private final WizardRepository wizardRepository;

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
        Wizard existingWizardAdopter = wizardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
        existingWizardAdopter.setFirstName(wizardAdopter.getFirstName());
        existingWizardAdopter.setLastName(wizardAdopter.getLastName());
        existingWizardAdopter.setEmail(wizardAdopter.getEmail());
        existingWizardAdopter.setPassword(wizardAdopter.getPassword());
        existingWizardAdopter.setShelterRole(wizardAdopter.getShelterRole());
        existingWizardAdopter.setWizardHouse(wizardAdopter.getWizardHouse());
        existingWizardAdopter.setAdoptions(wizardAdopter.getAdoptions());
        wizardRepository.save(existingWizardAdopter);
    }

    @Override
    public void deleteById(Long id) {
        if (!wizardRepository.existsById(id)) {
            throw new RuntimeException("id not found");
        }
        wizardRepository.deleteById(id);
    }
}

package org.example.petwards.bll.impls;

import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.StaffService;
import org.example.petwards.dal.repositories.WizardRepository;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private WizardRepository wizardRepository;

    @Autowired
    public StaffServiceImpl(WizardRepository wizardRepository) {
        this.wizardRepository = wizardRepository;
    }
    @Override
    public Wizard createStaff(Wizard wizard) {
        wizard.setShelterRole(ShelterRole.STAFF);
        wizardRepository.save(wizard);
        return wizard;
    }

    @Override
    public Wizard findById(Long id) {
        return wizardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
    }

    @Override
    public List<Wizard> findAll() {
        return wizardRepository.findAll();
    }

    @Override
    public void updateStaff(Long id, Wizard wizard) {
        Wizard existingWizard = wizardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
        existingWizard.setFirstName(wizard.getFirstName());
        existingWizard.setLastName(wizard.getLastName());
        existingWizard.setPassword(wizard.getPassword());
        existingWizard.setEmail(wizard.getEmail());
        existingWizard.setAdoptions(wizard.getAdoptions());
        existingWizard.setWizardHouse(wizard.getWizardHouse());
        wizardRepository.save(existingWizard);
    }

    @Override
    public void deleteById(Long id) {
        if (!wizardRepository.existsById(id)) {
            throw new RuntimeException("id not found");
        }
        wizardRepository.deleteById(id);
    }

    @Override
    public List<Wizard> getAllStaffs() {
        return wizardRepository.findAll();
    }
}

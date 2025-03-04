package org.example.petwards.bll.impls;

import org.example.petwards.bll.StaffService;
import org.example.petwards.dal.repositories.WizardRepository;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.ShelterRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StaffServiceImpl implements StaffService {
    private WizardRepository wizardRepository;

    @Override
    public Wizard CreateWizard(Wizard wizard, ShelterRole role) {
        if (wizardRepository.existsById(wizard.getId())) {
            if (role == wizard.getShelterRole()) {
                wizard.setShelterRole(role);
            }
        }
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
    public void updateStaff(Long id, Wizard wizard, ShelterRole role) {

    }

    @Override
    public void deleteById(Long id) {
        if (!wizardRepository.existsById(id)) {
            throw new RuntimeException("id not found");
        }
        wizardRepository.deleteById(id);
    }

    @Override
    public List<Wizard> getAllStaffs(Wizard wizard, ShelterRole role) {
            List<Wizard> staffs = new ArrayList<>();


            return null;

    }
}

package org.example.petwards.bll;

import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.AdoptionStatus;
import org.example.petwards.dl.enums.ShelterRole;

import java.util.List;

public interface StaffService  {
    Wizard CreateWizard(Wizard wizard, ShelterRole role);
    Wizard findById(Long id);
    List<Wizard> findAll();
    void updateStaff(Long id, Wizard wizard, ShelterRole role);
    void deleteById(Long id);
    List<Wizard> getAllStaffs(Wizard wizard, ShelterRole role);

}

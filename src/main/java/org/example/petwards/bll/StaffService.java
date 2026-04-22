package org.example.petwards.bll;

import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.AdoptionStatus;
import org.example.petwards.dl.enums.ShelterRole;

import java.util.List;

public interface StaffService  {
    Wizard createStaff(Wizard wizard);
    Wizard findById(Long id);
    List<Wizard> findAll();
    void updateStaff(Long id, Wizard wizard);
    void deleteById(Long id);
    List<Wizard> getAllStaffs();

}

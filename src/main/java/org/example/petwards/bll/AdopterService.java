package org.example.petwards.bll;

import org.example.petwards.api.models.wizards.dtos.WizardDTO;
import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.entities.Wizard;
import org.example.petwards.dl.enums.AdoptionStatus;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdopterService {
    Wizard save (Wizard wizardAdopter);
    Wizard findById(Long id);
    List<Wizard> findAll();
    void updateAdopter(Long id, Wizard wizardAdopter);
    void deleteById(Long id);
    void deleteAdopter(Long id);
    List<Wizard> getAllAdopters(AdoptionStatus status, Wizard wizard);




}

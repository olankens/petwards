package org.example.petwards.bll;



import org.example.petwards.dl.entities.Wizard;

import java.util.List;

public interface WizardService {
    Wizard save (Wizard wizard);
    Wizard findById(Long id);
    List<Wizard> findAll();
    void update(Long id, Wizard wizard);
    void deleteById(Long id);
}

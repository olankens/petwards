package org.example.petwards.bll;



import org.example.petwards.dl.entities.Wizard;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface WizardService extends UserDetailsService {
    Wizard save (Wizard wizard);
    Wizard findById(Long id);
    List<Wizard> findAll();
    void update(Long id, Wizard wizard);
    void deleteById(Long id);
}

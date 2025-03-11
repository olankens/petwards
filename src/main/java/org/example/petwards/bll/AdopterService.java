package org.example.petwards.bll;

import org.example.petwards.dl.entities.Wizard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface AdopterService {
    Page<Wizard> getAllAdopters(Pageable pageable);

    Wizard save(Wizard wizardAdopter);

    Wizard findById(Long id);

    List<Wizard> findAll();

    void updateAdopter(Long id, Wizard wizardAdopter);

    void deleteById(Long id);

    void deleteAdopter(Long id);
}

package org.example.petwards.bll;

import org.example.petwards.dl.entities.Wizard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdopterService {
    Page<Wizard> getAllAdopters(Pageable pageable);

    Wizard save(Wizard adopter);

    Wizard findById(Long id);

    void updateAdopter(Long id, Wizard adopter);

    void deleteById(Long id);

    void adoptBeast(Long beastId, Wizard adopter);
}

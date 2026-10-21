package org.example.petwards.bll;

import org.example.petwards.dl.entities.Adoption;

import java.util.List;

public interface AdoptionService {
    Adoption save(Adoption adoption);

    Adoption findById(Long id);

    List<Adoption> findAll();

    void update(Long id, Adoption adoption);

    void deleteById(Long id);

    List<Adoption> getPendingAdoption();

    List<Adoption> getApproveAdoption();

    Adoption approveAdoption(Long id);

    Adoption rejectAdoption(Long id);
}

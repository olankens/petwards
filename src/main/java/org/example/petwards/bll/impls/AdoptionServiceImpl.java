package org.example.petwards.bll.impls;


import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.AdoptionService;
import org.example.petwards.dal.repositories.AdoptionRepository;
import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.enums.AdoptionStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;
    @Override
    public Adoption save(Adoption adoption) {
        if (adoptionRepository.existsById(adoption.getId())) {
            throw new RuntimeException("id already exists");
        }
        adoptionRepository.save(adoption);
        return adoption;
    }

    @Override
    public Adoption findById(Long id) {
        return adoptionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
    }

    @Override
    public List<Adoption> findAll() {
        return adoptionRepository.findAll();
    }

    @Override
    public void update(Long id, Adoption adoption) {
        Adoption existingAdoption = adoptionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
        existingAdoption.setStatus(adoption.getStatus());
        existingAdoption.setBeast(adoption.getBeast());
        existingAdoption.setWizard(adoption.getWizard());
        adoptionRepository.save(existingAdoption);

    }

    @Override
    public void deleteById(Long id) {
        if (!adoptionRepository.existsById(id)) {
            throw new RuntimeException("id not found");
        }
        adoptionRepository.deleteById(id);

    }

    @Override
    public List<Adoption> getPendingAdoption() {
        return adoptionRepository.findByStatus(AdoptionStatus.PENDING);
    }

    @Override
    public List<Adoption> getApproveAdoption() {
        return adoptionRepository.findByStatus(AdoptionStatus.APPROVED);
    }


    @Override
    public void approveAdoption(Long id) {
        Adoption adoption = findById(id);
        adoption.setStatus(AdoptionStatus.APPROVED);
        adoptionRepository.save(adoption);
    }

    @Override
    public void rejectAdoption(Long id) {
        Adoption adoption = findById(id);
        adoption.setStatus(AdoptionStatus.REJECTED);
        adoptionRepository.save(adoption);
    }
}

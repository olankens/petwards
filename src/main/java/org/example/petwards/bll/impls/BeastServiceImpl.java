package org.example.petwards.bll.impls;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.BeastService;
import org.example.petwards.dal.repositories.BeastRepository;
import org.example.petwards.dal.repositories.CapabilityRepository;
import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.entities.Capability;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeastServiceImpl implements BeastService {

    private final BeastRepository beastRepository;

    private final CapabilityRepository capabilityRepository;

    @Override
    public Beast save(Beast beast) {
        if (beastRepository.existsById(beast.getId())){
            throw  new RuntimeException("id already exists");
        }
        beastRepository.save(beast);
        return beast;
    }

    @Override
    public Beast findById(Long id) {
        return beastRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
    }

    @Override
    public List<Beast> findAll() {
        return beastRepository.findAll();
    }

    @Override
    public void update(Long id, Beast beast) {
        Beast existingBeast = beastRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
        existingBeast.setName(beast.getName());
        existingBeast.setAvailable(beast.isAvailable());
        existingBeast.setDangerLevel(beast.getDangerLevel());
        existingBeast.setAdoption(beast.getAdoption());
        beastRepository.save(existingBeast);

    }

    @Override
    public void deleteById(Long id) {
        if (!beastRepository.existsById(id)){
            throw  new RuntimeException("id not found");
        }
        beastRepository.deleteById(id);

    }
    public void addCapabilityToBeast(Long beastId, Long capabilityId) {
        Beast beast = beastRepository.findById(beastId)
                .orElseThrow(() -> new EntityNotFoundException("Beast not found"));

        Capability capability = capabilityRepository.findById(capabilityId)
                .orElseThrow(() -> new EntityNotFoundException("Capability not found"));

        beast.getCapabilities().add(capability);  // Ajoute la capability
        beastRepository.save(beast);
    }

    public void removeCapabilityFromBeast(Long beastId, Long capabilityId) {
        Beast beast = beastRepository.findById(beastId)
                .orElseThrow(() -> new EntityNotFoundException("Beast not found"));

        Capability capability = capabilityRepository.findById(capabilityId)
                .orElseThrow(() -> new EntityNotFoundException("Capability not found"));

        beast.getCapabilities().remove(capability);  // Retire la capability
        beastRepository.save(beast);
    }



}

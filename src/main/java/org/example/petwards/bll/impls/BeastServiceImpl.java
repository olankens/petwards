package org.example.petwards.bll.impls;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.BeastService;
import org.example.petwards.dal.repositories.BeastRepository;
import org.example.petwards.dal.repositories.CapabilityRepository;
import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.entities.Capability;
import org.example.petwards.il.filters.specifications.BeastSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeastServiceImpl implements BeastService {

    private final BeastRepository beastRepository;
    private final CapabilityRepository capabilityRepository;

    @Override
    public Beast createBeast(Beast beast) {
        beast.setAdoptions(beast.getAdoptions());
        beast.setAvailable(beast.isAvailable());
        beast.setDangerLevel(beast.getDangerLevel());
        beastRepository.save(beast);
        return beast;
    }

    @Override
    public Beast save(Beast beast) {
        if (beastRepository.existsById(beast.getId())) {
            throw new RuntimeException("id already exists");
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
    public Page<Beast> findAllByNameAndCapability(Pageable pageable, String name, List<String> capabilities) {
        Specification<Beast> spec = Specification.where(BeastSpecification.hasNameLike(name))
                .and(BeastSpecification.joinCapabilities())
                .and(BeastSpecification.hasCapabilities(capabilities));
        return beastRepository.findAll(spec, pageable);
    }

    @Override
    public Page<Beast> findAll(Pageable pageable) {
        return beastRepository.findAll(pageable);
    }

    @Override
    public void update(Long id, Beast beast) {
        Beast existingBeast = beastRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
        existingBeast.setName(beast.getName());
        existingBeast.setAvailable(beast.isAvailable());
        existingBeast.setDangerLevel(beast.getDangerLevel());
        existingBeast.setAdoptions(beast.getAdoptions());
        beastRepository.save(existingBeast);
    }

    @Override
    public void deleteById(Long id) {
        if (!beastRepository.existsById(id)) {
            throw new RuntimeException("id not found");
        }
        beastRepository.deleteById(id);
    }

    public void addCapabilityToBeast(Long beastId, Long capabilityId) {
        Beast beast = beastRepository.findById(beastId)
                .orElseThrow(() -> new EntityNotFoundException("Beast not found"));
        Capability capability = capabilityRepository.findById(capabilityId)
                .orElseThrow(() -> new EntityNotFoundException("Capability not found"));
        beast.getCapabilities().add(capability);
        beastRepository.save(beast);
    }

    public void removeCapabilityFromBeast(Long beastId, Long capabilityId) {
        Beast beast = beastRepository.findById(beastId)
                .orElseThrow(() -> new EntityNotFoundException("Beast not found"));

        Capability capability = capabilityRepository.findById(capabilityId)
                .orElseThrow(() -> new EntityNotFoundException("Capability not found"));

        beast.getCapabilities().remove(capability);
        beastRepository.save(beast);
    }
}

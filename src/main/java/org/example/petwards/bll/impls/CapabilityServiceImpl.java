package org.example.petwards.bll.impls;

import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.CapabilityService;
import org.example.petwards.dal.repositories.CapabilityRepository;
import org.example.petwards.dl.entities.Capability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CapabilityServiceImpl implements CapabilityService {
    private final CapabilityRepository capabilityRepository;

    @Override
    public Capability createCapability(Capability capability) {
        capability.setName(capability.getName());
        capabilityRepository.save(capability);
        return capability;
    }

    @Override
    public Capability findOrCreateCapabilityByName(String name) {
        return capabilityRepository.findByName(name)
                .orElseGet(() -> {
                    Capability newCapability = new Capability();
                    newCapability.setName(name);
                    return capabilityRepository.save(newCapability);
                });
    }

    @Override
    public Capability save(Capability capability) {
        if (capabilityRepository.existsById(capability.getId())){
            throw new RuntimeException("id already exists");
        }
        capabilityRepository.save(capability);
        return capability;
    }

    @Override
    public Capability findById(Long id) {
        return capabilityRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
    }

    @Override
    public Page<Capability> findAll(Pageable pageable) {
        return capabilityRepository.findAll(pageable);
    }

    @Override
    public void update(Long id, Capability capability) {
        Capability existigCapability = capabilityRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
        existigCapability.setName(capability.getName());
        capabilityRepository.save(existigCapability);

    }

    @Override
    public void deleteById(Long id) {
        if (!capabilityRepository.existsById(id)){
            throw new RuntimeException("id not found");
        }
        capabilityRepository.deleteById(id);
    }
}

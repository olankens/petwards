package org.example.petwards.bll;


import org.example.petwards.dl.entities.Capability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CapabilityService {
    Capability save(Capability capability);

    Capability findById(Long id);

    Page<Capability> findAll(Pageable pageable);

    void update(Long id, Capability capability);

    void deleteById(Long id);

    Capability createCapability(Capability capability);

    Capability findOrCreateCapabilityByName(String name);
}

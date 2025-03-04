package org.example.petwards.bll;



import org.example.petwards.dl.entities.Capability;

import java.util.List;

public interface CapabilityService {
    Capability save (Capability capability);
    Capability findById(Long id);
    List<Capability> findAll();
    void update(Long id, Capability capability);
    void deleteById(Long id);
}

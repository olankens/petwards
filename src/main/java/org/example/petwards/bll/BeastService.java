package org.example.petwards.bll;

import org.example.petwards.dl.entities.Beast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BeastService {
    Beast save(Beast beast);

    Beast createBeast(Beast beast);

    Beast findById(Long id);

    Page<Beast> findAll(Pageable pageable);

    Page<Beast> findAllByNameAndCapability(Pageable pageable, String name, List<String> capabilities);

    void update(Long id, Beast beast);

    void deleteById(Long id);

    public void addCapabilityToBeast(Long beastId, Long capabilityId);

    public void removeCapabilityFromBeast(Long beastId, Long capabilityId);
}

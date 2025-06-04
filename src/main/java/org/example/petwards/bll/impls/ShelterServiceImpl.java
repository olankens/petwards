package org.example.petwards.bll.impls;

import jakarta.persistence.NamedQueries;
import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.ShelterService;
import org.example.petwards.dal.repositories.ShelterRepository;
import org.example.petwards.dl.entities.Shelter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;


    @Override
    public Shelter findById(Long id) {
        return shelterRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
    }

    @Override
    public List<Shelter> findAll() {
        return shelterRepository.findAll();
    }

    @Override
    public void update(Long id, Shelter shelter) {
        Shelter existingShelter = shelterRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
        existingShelter.setName(shelter.getName());
        existingShelter.setDescription(shelter.getDescription());
        shelterRepository.save(existingShelter);
    }

}

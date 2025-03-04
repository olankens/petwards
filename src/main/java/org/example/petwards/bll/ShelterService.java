package org.example.petwards.bll;



import org.example.petwards.dl.entities.Shelter;

import java.util.List;

public interface ShelterService {
    Shelter findById(Long id);
    List<Shelter> findAll();
    void update(Long id, Shelter shelter);

}

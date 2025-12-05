package org.example.petwards.dal.repositories;

import org.example.petwards.dl.entities.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Optional<Shelter> findById(Long id);
    boolean existsById(Long id);

}

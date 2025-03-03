package org.example.petwards.dal.repositories;

import org.example.petwards.dl.entities.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    Optional<Adoption> findById(Long id);
    boolean existsById(Long id);
}

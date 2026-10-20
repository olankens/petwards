package org.example.petwards.dal.repositories;

import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.enums.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    Optional<Adoption> findById(Long id);
    List<Adoption> findByStatus (AdoptionStatus status);
    boolean existsById(Long id);

    @Query("SELECT a FROM Adoption a WHERE a.status = 'pending'")
    List<Adoption> findPendingAdoptions();
}

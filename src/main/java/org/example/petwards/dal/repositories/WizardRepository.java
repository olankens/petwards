package org.example.petwards.dal.repositories;

import org.example.petwards.dl.entities.Wizard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WizardRepository extends JpaRepository<Wizard, Long> {
    Optional<Wizard> findByEmail(String email);

    boolean existsByEmail(String email);
}

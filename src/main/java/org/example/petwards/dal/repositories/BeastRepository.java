package org.example.petwards.dal.repositories;

import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.entities.Capability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.Set;

public interface BeastRepository extends JpaRepository<Beast, Long>, JpaSpecificationExecutor<Beast> {
    Optional<Beast> findById(Long id);
    boolean existsById(Long id);
    Page<Beast> findAllByNameLikeIgnoreCaseAndCapabilitiesContainingIgnoreCase(
            Pageable pageable, String name, Set<Capability> capabilities
    );
}

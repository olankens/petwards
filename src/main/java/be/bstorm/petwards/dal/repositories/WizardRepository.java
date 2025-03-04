package be.bstorm.petwards.dal.repositories;

import be.bstorm.petwards.dl.entities.Wizard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardRepository extends JpaRepository<Wizard, Long> {
}

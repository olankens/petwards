package org.example.petwards.dal.initializers;


import lombok.RequiredArgsConstructor;
import org.example.petwards.dal.repositories.*;
import org.example.petwards.dl.entities.*;
import org.example.petwards.dl.enums.AdoptionStatus;
import org.example.petwards.dl.enums.DangerLevel;
import org.example.petwards.dl.enums.ShelterRole;
import org.example.petwards.dl.enums.WizardHouse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final WizardRepository wizardRepository;
    private final BeastRepository beastRepository;
    private final CapabilityRepository capabilityRepository;
    private final AdoptionRepository adoptionRepository;
    private final ShelterRepository shelterRepository;

    @Override
    public void run(String... args) throws Exception {
        saveDefaultWizards();
        saveDefaultBeasts();
        saveDefaultShelters();
    }

    public void saveDefaultWizards() {
        if (wizardRepository.count() == 0) {
            List<Wizard> wizards = List.of(
                    new Wizard("albus wilfred percival brian", "Dumbledore", "dumby@gmail.mag", ShelterRole.ADMIN, WizardHouse.GRYFFINDOR),
                    new Wizard("test", "test", "test@petward.h", ShelterRole.STAFF, WizardHouse.SLYTHERIN));
            wizardRepository.saveAll(wizards);
        }
    }

    public void saveDefaultBeasts() {
        if (beastRepository.count() == 0) {
            //Capability
            Set<Capability> capabilities = Set.of(
                    new Capability("fire"),
                    new Capability("frozen"),
                    new Capability("flight"),
                    new Capability("small"),
                    new Capability("big"));
            capabilityRepository.saveAll(capabilities);

            //Adoption
            Adoption adoption = new Adoption(AdoptionStatus.PENDING);
            adoptionRepository.save(adoption);

            //Beast
            List<Beast> beasts = List.of(
                    new Beast("Smaug", true, DangerLevel.INSANE, adoption, capabilities),
                    new Beast("Phoenix des Glace", true, DangerLevel.HIGH, adoption, capabilities),
                    new Beast("Dylan", true, DangerLevel.LOW, adoption, capabilities),
                    new Beast("Croutard", true, DangerLevel.MEDIUM, adoption, capabilities)
            );
            beastRepository.saveAll(beasts);
        }
    }

    public void saveDefaultShelters() {
        Shelter shelter = new Shelter("petward", "Centre d'adoption de créature magique en perdition");
        shelterRepository.save(shelter);
    }
}

package org.example.petwards.dal.initializers;


import lombok.RequiredArgsConstructor;
import org.example.petwards.dal.repositories.AdoptionRepository;
import org.example.petwards.dal.repositories.BeastRepository;
import org.example.petwards.dal.repositories.CapabilityRepository;
import org.example.petwards.dal.repositories.WizardRepository;
import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.entities.Capability;
import org.example.petwards.dl.entities.Wizard;
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

    @Override
    public void run(String... args) throws Exception {
        saveDefaultWizards();
        saveDefaultBeasts();
    }

    public void saveDefaultWizards() {
        if (wizardRepository.count() == 0) {
            Wizard wizard = new Wizard("albus wilfred percival brian", "Dumbledore", "dumby@gmail.mag", ShelterRole.ADMIN, WizardHouse.GRYFFINDOR);
            wizardRepository.save(wizard);
        }
    }

    public void saveDefaultBeasts(){
        if (beastRepository.count() == 0){
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
            adoption = adoptionRepository.save(adoption);

            //Beast
            List<Beast> beasts = List.of(
                    new Beast("Smaug", true, DangerLevel.INSANE,adoption,capabilities ),
                    new Beast("Phoenix des Glace", true, DangerLevel.HIGH, adoption, capabilities),
                    new Beast("Dylan", true, DangerLevel.LOW, adoption, capabilities),
                    new Beast("Croutard",true,DangerLevel.MEDIUM, adoption, capabilities)
            );
            beastRepository.saveAll(beasts);
        }
    }
}

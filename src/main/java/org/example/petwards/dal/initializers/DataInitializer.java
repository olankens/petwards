package org.example.petwards.dal.initializers;


import lombok.RequiredArgsConstructor;
import org.example.petwards.dal.repositories.*;
import org.example.petwards.dl.entities.*;
import org.example.petwards.dl.enums.AdoptionStatus;
import org.example.petwards.dl.enums.DangerLevel;
import org.example.petwards.dl.enums.ShelterRole;
import org.example.petwards.dl.enums.WizardHouse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final WizardRepository wizardRepository;
    private final BeastRepository beastRepository;
    private final CapabilityRepository capabilityRepository;
    private final ShelterRepository shelterRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        saveDefaultWizards();
        saveDefaultBeasts();
        saveDefaultShelters();
        saveDefaultAdoptions();
    }

    public void saveDefaultWizards() {
        if (wizardRepository.count() == 0) {
            String defaultPasswordForAdmins = "admin";
            String defaultPasswordForStaffs = "staff";
            String defaultPasswordForAdopters = "adopter";
            List<Wizard> admins = List.of(
                    new Wizard(
                            "Albus Wilfred Percival Brian",
                            "Dumbledore",
                            "admin@gmail.com",
                            passwordEncoder.encode(defaultPasswordForAdmins),
                            WizardHouse.GRYFFINDOR,
                            ShelterRole.ADMIN
                    )
            );
            List<Wizard> staffs = List.of(
                    new Wizard(
                            "Minerva",
                            "McGonagall",
                            "minerva@gmail.com",
                            passwordEncoder.encode(defaultPasswordForStaffs),
                            WizardHouse.GRYFFINDOR,
                            ShelterRole.STAFF
                    ),
                    new Wizard(
                            "Severus",
                            "Snape",
                            "severus@gmail.com",
                            passwordEncoder.encode(defaultPasswordForStaffs),
                            WizardHouse.SLYTHERIN,
                            ShelterRole.STAFF
                    ),
                    new Wizard(
                            "Filius",
                            "Flitwick",
                            "filius@gmail.com",
                            passwordEncoder.encode(defaultPasswordForStaffs),
                            WizardHouse.RAVENCLAW,
                            ShelterRole.STAFF
                    ),
                    new Wizard(
                            "Pomona",
                            "Sprout",
                            "pomona@gmail.com",
                            passwordEncoder.encode(defaultPasswordForStaffs),
                            WizardHouse.HUFFLEPUFF,
                            ShelterRole.STAFF
                    ),
                    new Wizard(
                            "Remus",
                            "Lupin",
                            "remus@gmail.com",
                            passwordEncoder.encode(defaultPasswordForStaffs),
                            WizardHouse.GRYFFINDOR,
                            ShelterRole.STAFF
                    ),
                    new Wizard(
                            "Sybill",
                            "Trelawney",
                            "sybill@gmail.com",
                            passwordEncoder.encode(defaultPasswordForStaffs),
                            WizardHouse.RAVENCLAW,
                            ShelterRole.STAFF
                    ),
                    new Wizard(
                            "Gilderoy",
                            "Lockhart",
                            "gilderoy@gmail.com",
                            passwordEncoder.encode(defaultPasswordForStaffs),
                            WizardHouse.RAVENCLAW,
                            ShelterRole.STAFF
                    ),
                    new Wizard(
                            "Horace",
                            "Slughorn",
                            "horace@gmail.com",
                            passwordEncoder.encode(defaultPasswordForStaffs),
                            WizardHouse.SLYTHERIN,
                            ShelterRole.STAFF
                    )
            );
            List<Wizard> adopters = List.of(
                    new Wizard(
                            "Harry",
                            "Potter",
                            "harry@gmail.com",
                            passwordEncoder.encode(defaultPasswordForAdopters),
                            WizardHouse.GRYFFINDOR,
                            ShelterRole.ADOPTER
                    ),
                    new Wizard(
                            "Draco",
                            "Malfoy",
                            "draco@gmail.com",
                            passwordEncoder.encode(defaultPasswordForAdopters),
                            WizardHouse.SLYTHERIN,
                            ShelterRole.ADOPTER
                    ),
                    new Wizard(
                            "Luna",
                            "Lovegood",
                            "luna@gmail.com",
                            passwordEncoder.encode(defaultPasswordForAdopters),
                            WizardHouse.RAVENCLAW,
                            ShelterRole.ADOPTER
                    ),
                    new Wizard(
                            "Cedric",
                            "Diggory",
                            "cedric@gmail.com",
                            passwordEncoder.encode(defaultPasswordForAdopters),
                            WizardHouse.HUFFLEPUFF,
                            ShelterRole.ADOPTER
                    )
            );
            wizardRepository.saveAll(admins);
            wizardRepository.saveAll(staffs);
            wizardRepository.saveAll(adopters);
        }
    }

    public void saveDefaultBeasts() {
        if (beastRepository.count() == 0) {
            Set<Capability> forLow = Set.of(new Capability("coffee"), new Capability("cooking"));
            Set<Capability> forMedium = Set.of(new Capability("flying"), new Capability("proud"));
            Set<Capability> forHigh = Set.of(new Capability("fire-breathing"), new Capability("acid-spit"));
            Set<Capability> forInsane = Set.of(new Capability("mind-control"), new Capability("reality-distortion"));
            Set<Capability> forAll = Stream.of(forLow, forMedium, forHigh, forInsane).flatMap(Set::stream).collect(Collectors.toSet());
            if (capabilityRepository.count() == 0) capabilityRepository.saveAll(forAll);
            List<Beast> beasts = List.of(
                    new Beast(
                            "Basilisk of the Chamber",
                            true,
                            DangerLevel.INSANE,
                            null,
                            forLow
                    ),
                    new Beast(
                            "Aragog the Acromantula",
                            true,
                            DangerLevel.HIGH,
                            null,
                            forHigh
                    ),
                    new Beast(
                            "Buckbeak the Hippogriff",
                            true,
                            DangerLevel.MEDIUM,
                            null,
                            forMedium
                    ),
                    new Beast(
                            "Majestic Dylan",
                            true,
                            DangerLevel.LOW,
                            null,
                            forLow
                    )
            );
            beastRepository.saveAll(beasts);
        }
    }

    public void saveDefaultShelters() {
        Shelter shelter = new Shelter("petward", "");
        shelterRepository.save(shelter);


    }

    public void saveDefaultAdoptions() {
        Beast fret = beastRepository.findById(4L).orElseThrow();
        Wizard wiz = wizardRepository.findById(3L).orElseThrow();
        wiz.getAdoptions().add(new Adoption(AdoptionStatus.PENDING,fret,wiz ));
        wizardRepository.save(wiz);
    }
}

package be.bstorm.petwards.dal;

import lombok.RequiredArgsConstructor;
import be.bstorm.petwards.dal.repositories.WizardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final WizardRepository wizardRepository;

    @Override
    public void run(String... args) throws Exception {
        saveDefaultWizards();
    }

    public void saveDefaultWizards() {
        if (wizardRepository.count() == 0) {
            // wizardRepository.saveAll(List.of());
        }
    }
}

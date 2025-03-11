package org.example.petwards.bll.impls;


import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.AdoptionService;
import org.example.petwards.bll.EmailService;
import org.example.petwards.bll.exceptions.AdoptionNotFoundException;
import org.example.petwards.dal.repositories.AdoptionRepository;
import org.example.petwards.dl.entities.Adoption;
import org.example.petwards.dl.enums.AdoptionStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final EmailService emailService;

    @Override
    public Adoption save(Adoption adoption) {
        if (adoptionRepository.existsById(adoption.getId())) {
            throw new RuntimeException("id already exists");
        }
        adoptionRepository.save(adoption);
        return adoption;
    }

    @Override
    public Adoption findById(Long id) {
        return adoptionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
    }

    @Override
    public List<Adoption> findAllByPending() {
        return adoptionRepository.findByStatus(AdoptionStatus.PENDING);
    }

    @Override
    public List<Adoption> findAll() {
        return adoptionRepository.findAll();
    }

    @Override
    public void update(Long id, Adoption adoption) {
        Adoption existingAdoption = adoptionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id not found")
        );
        existingAdoption.setStatus(adoption.getStatus());
        existingAdoption.setBeast(adoption.getBeast());
        existingAdoption.setWizard(adoption.getWizard());
        adoptionRepository.save(existingAdoption);

    }

    @Override
    public void deleteById(Long id) {
        if (!adoptionRepository.existsById(id)) {
            throw new RuntimeException("id not found");
        }
        adoptionRepository.deleteById(id);

    }

    @Override
    public List<Adoption> getPendingAdoption() {
        return adoptionRepository.findByStatus(AdoptionStatus.PENDING);
    }

    @Override
    public List<Adoption> getApproveAdoption() {
        return adoptionRepository.findByStatus(AdoptionStatus.APPROVED);
    }


//    @Override
//    public Adoption approveAdoption(Long id, String adoptionEmail) {
//        Adoption adoption = findById(id);
//        adoption.setStatus(AdoptionStatus.APPROVED);
//        adoptionRepository.save(adoption);
//
//        String subject = "Adoption Acceptée";
//        String text = "Félicitations! Votre adoption a été acceptée.";
//        try {
//            emailService.sendEmail(adoptionEmail, subject, text);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//
//        return adoption;
//    }

    public Adoption approveAdoption(Long adoptionId) {
        Adoption adoption = adoptionRepository.findById(adoptionId)
                .orElseThrow(() -> new AdoptionNotFoundException("Adoption not found"));

        adoption.setStatus(AdoptionStatus.APPROVED); // Mettre à jour le statut de l'adoption
        adoptionRepository.save(adoption);

        // Envoyer un email pour informer de l'acceptation
        String subject = "Adoption Acceptée";
        String text = "Félicitations! Votre adoption a été acceptée.";
        try {
            emailService.sendEmail(adoption.getWizard().getEmail(), subject, text);  // Envoie l'email à l'adoptant
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return adoption;
    }

//    @Override
//    public Adoption rejectAdoption(Long id, String adoptionEmail) {
//        Adoption adoption = findById(id);
//        adoption.setStatus(AdoptionStatus.REJECTED);
//        adoptionRepository.save(adoption);
//
//        String subject = "Adoption Refusée";
//        String text = "Désolé, votre adoption a été refusée. Nous vous remercions de votre intérêt.";
//        try {
//            emailService.sendEmail(adoptionEmail, subject, text);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//        return adoption;
//    }

    public Adoption rejectAdoption(Long adoptionId) {
        Adoption adoption = adoptionRepository.findById(adoptionId)
                .orElseThrow(() -> new AdoptionNotFoundException("Adoption not found"));

        adoption.setStatus(AdoptionStatus.REJECTED); // Mettre à jour le statut de l'adoption
        adoptionRepository.save(adoption);

        // Envoyer un email pour informer du refus
        String subject = "Adoption Refusée";
        String text = "Désolé, votre adoption a été refusée. Nous vous remercions de votre intérêt.";
        try {
            emailService.sendEmail(adoption.getWizard().getEmail(), subject, text);  // Envoie l'email à l'adoptant
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return adoption;
    }
}

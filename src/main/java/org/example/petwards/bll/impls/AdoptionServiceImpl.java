package org.example.petwards.bll.impls;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.petwards.bll.AdoptionService;
import org.example.petwards.bll.EmailService;
import org.example.petwards.bll.exceptions.PetwardsAdoptionNotFoundException;
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

    public Adoption approveAdoption(Long adoptionId) {
        Adoption adoption = adoptionRepository.findById(adoptionId).orElseThrow(
                () -> new PetwardsAdoptionNotFoundException("Adoption not found")
        );
        adoption.setStatus(AdoptionStatus.APPROVED);
        adoptionRepository.save(adoption);
        adoption.getBeast().setAvailable(false);
        String subject = "Adoption Accepted";
        String text = "Congratulations! Your adoption has been accepted.";
        try {
            emailService.sendEmail(adoption.getWizard().getEmail(), subject, text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return adoption;
    }

    public Adoption rejectAdoption(Long adoptionId) {
        Adoption adoption = adoptionRepository.findById(adoptionId).orElseThrow(
                () -> new PetwardsAdoptionNotFoundException("Adoption not found")
        );
        adoption.setStatus(AdoptionStatus.REJECTED);
        adoptionRepository.save(adoption);
        String subject = "Adoption Rejected";
        String text = "Sorry, your adoption has been rejected. We thank you for your interest.";
        try {
            emailService.sendEmail(adoption.getWizard().getEmail(), subject, text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return adoption;
    }
}

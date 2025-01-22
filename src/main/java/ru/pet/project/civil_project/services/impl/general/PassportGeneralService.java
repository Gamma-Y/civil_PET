package ru.pet.project.civil_project.services.impl.general;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.Passport;
import ru.pet.project.civil_project.db.repositories.PassportRepository;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;
import ru.pet.project.civil_project.services.GeneralService;

import java.util.List;
import java.util.Optional;

/**
 * @author Gamma on 22.01.2025
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportGeneralService implements GeneralService<Passport, Long> {

    private final JpaRepository<Passport, Long> passportRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Passport> findAll() {
        log.debug("Try find all passports");
        return passportRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Passport> findById(Long id) {
        log.debug("Try find passport with id: {}", id);
        return passportRepository.findById(id);
    }

    @Override
    @Transactional
    public Passport save(Passport entity) {
        log.debug("Try save passport: {}", entity);
        return passportRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Try delete passport with id: {}", id);
        passportRepository.findById(id)
                .ifPresentOrElse(
                        passportRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Passport", id);
                        }
                );
    }
}

package ru.pet.project.civil_project.services.impl.general;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.Resident;
import ru.pet.project.civil_project.db.repositories.ResidentRepository;
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
public class ResidentsGeneralService implements GeneralService<Resident, Long> {

    private final ResidentRepository residentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Resident> findAll() {
        log.debug("Try find all residents");
        return residentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Resident> findById(Long id) {
        log.debug("Try find resident with id: {}", id);
        return residentRepository.findById(id);
    }

    @Override
    @Transactional
    public Resident save(Resident entity) {
        log.debug("Try save resident: {}", entity);
        return residentRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Try delete resident with id: {}", id);
        residentRepository.findById(id)
                .ifPresentOrElse(
                        residentRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Resident", id);
                        }
                );
    }
}

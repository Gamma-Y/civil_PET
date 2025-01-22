package ru.pet.project.civil_project.services.impl.general;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.pet.project.civil_project.db.entities.House;
import ru.pet.project.civil_project.db.repositories.HouseRepository;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;
import ru.pet.project.civil_project.services.GeneralService;

/**
 * @author Gamma on 22.01.2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HouseGeneralService implements GeneralService<House, Long> {

    private final HouseRepository houseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<House> findAll() {
        log.debug("Try find all houses");
        return houseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<House> findById(Long id) {
        log.debug("Try find house with id: {}", id);
        return houseRepository.findById(id);
    }

    @Override
    @Transactional
    public House save(House entity) {
        log.debug("Try save house: {}", entity);
        return houseRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Try delete house with id: {}", id);
        houseRepository.findById(id)
                .ifPresentOrElse(
                        houseRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("House", id);
                        }
                );
    }
}

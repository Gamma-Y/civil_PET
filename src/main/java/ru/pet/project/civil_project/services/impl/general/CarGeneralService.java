package ru.pet.project.civil_project.services.impl.general;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.Car;
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
public class CarGeneralService implements GeneralService<Car, Long> {

    private final JpaRepository<Car, Long> carRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        log.debug("Try find all cars");
        return carRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Car> findById(Long id) {
        log.debug("Try find car with id: {}", id);
        return carRepository.findById(id);
    }

    @Override
    @Transactional
    public Car save(Car entity) {
        log.debug("Try save car: {}", entity);
        return carRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Try delete car with id: {}", id);
        carRepository.findById(id)
                .ifPresentOrElse(
                        carRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Car", id);
                        }
                );
    }
}

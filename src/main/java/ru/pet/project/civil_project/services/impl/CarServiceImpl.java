package ru.pet.project.civil_project.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.Car;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;
import ru.pet.project.civil_project.services.CarService;
import ru.pet.project.civil_project.services.dto.car.SimpleCar;
import ru.pet.project.civil_project.services.impl.general.CarGeneralService;
import ru.pet.project.civil_project.services.mappers.CarMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author Gamma on 22.01.2025
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarGeneralService generalService;
    private final CarMapper carMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SimpleCar> getAll() {
        log.info("Fetching all cars");
        List<Car> cars = generalService.findAll();
        return carMapper.toSimpleCarDtos(cars);
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleCar getById(long id) {
        log.info("Fetching car with id: {}", id);
        Car car = generalService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
        return carMapper.toSimpleCarDto(car);
    }

    @Override
    @Transactional
    public SimpleCar add(SimpleCar dto) {
        log.info("Adding new car: {}", dto);
        Car car = carMapper.toCar(dto);
        car = generalService.save(car);
        return carMapper.toSimpleCarDto(car);
    }

    @Override
    @Transactional
    public SimpleCar update(long id, SimpleCar dto) {
        log.info("Updating car with id: {}", id);
        Car car = generalService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
        carMapper.updateCar(dto, car);
        car = generalService.save(car);
        return carMapper.toSimpleCarDto(car);
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.info("Deleting car with id: {}", id);
        Optional<Car> byId = generalService.findById(id);
        if (byId.isPresent()) {
            generalService.delete(id);
        } else throw new ResourceNotFoundException("Resident", id);
    }
}

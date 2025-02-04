package ru.pet.project.civil_project.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.Car;
import ru.pet.project.civil_project.db.entities.Resident;
import ru.pet.project.civil_project.db.repositories.CarRepository;
import ru.pet.project.civil_project.db.repositories.ResidentRepository;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;
import ru.pet.project.civil_project.services.CarService;
import ru.pet.project.civil_project.services.dto.car.ResidentCar;
import ru.pet.project.civil_project.services.dto.car.SimpleCar;
import ru.pet.project.civil_project.services.mappers.CarMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Gamma on 22.01.2025
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final ResidentRepository residentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SimpleCar> getAll() {
        log.info("Fetching all cars");
        List<Car> cars = carRepository.findAll();
        return carMapper.toSimpleCarDtos(cars);
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleCar getById(long id) {
        log.info("Fetching car with id: {}", id);
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
        return carMapper.toSimpleCarDto(car);
    }

    @Override
    @Transactional
    public SimpleCar add(ResidentCar dto) {
        log.info("Adding new car: {} to resident by id {}", dto, dto.residentId());
        Car car = carMapper.toCar(dto);
        Optional<Resident> byId = residentRepository.findById(dto.residentId());
        if (byId.isPresent()) {
            Resident resident = byId.get();
            resident.addCar(car);
            car = carRepository.save(car);
            return carMapper.toSimpleCarDto(car);
        } else throw new ResourceNotFoundException("Resident", dto.residentId());

    }

    @Override
    public SimpleCar update(long id, SimpleCar dto) {
        log.info("Updating car with id: {}", id);
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
        carMapper.updateCar(dto, car);
        car = carRepository.save(car);
        return carMapper.toSimpleCarDto(car);
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.info("Deleting car with id: {}", id);
        Optional<Car> byId = carRepository.findById(id);
        if (byId.isPresent()) {
            carRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Resident", id);
    }

    @Override
    @Transactional
    public void delete(Resident resident) {
        log.info("Deleting car for resident by id: {}", resident.getId());
        Set<Car> cars = resident.getCars();
        log.info("Found {} cars", cars.size());
        cars.forEach(Car::deleteResident);
        carRepository.deleteAll(cars);
    }
}

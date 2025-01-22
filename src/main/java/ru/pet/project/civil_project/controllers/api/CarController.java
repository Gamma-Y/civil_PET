package ru.pet.project.civil_project.controllers.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pet.project.civil_project.exception.BadRequestException;
import ru.pet.project.civil_project.services.CarService;
import ru.pet.project.civil_project.services.dto.car.SimpleCar;

import java.net.URI;
import java.util.List;

/**
 * @author Gamma on 22.01.2025
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {

    private static final String CAR_URL = "/api/v1/cars/";

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<SimpleCar>> getCars() {
        return ResponseEntity.ok(carService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleCar> getCar(@PathVariable @Positive long id) {
        return ResponseEntity.ok(carService.getById(id));
    }


    @PostMapping
    public ResponseEntity<SimpleCar> createCar(@RequestBody @Valid final SimpleCar car) {
        if (car == null) {
            throw new BadRequestException("Car object cannot be null");
        }
        SimpleCar simpleCar = carService.add(car);
        return ResponseEntity.created(URI.create(CAR_URL + simpleCar.id())).body(simpleCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleCar> updateCar(@PathVariable long id, @RequestBody @Valid SimpleCar car) {
        SimpleCar updateCar = carService.update(id, car);
        return ResponseEntity.ok(updateCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleCar> deleteCar(@PathVariable long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

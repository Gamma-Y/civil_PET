package ru.pet.project.civil_project.services;

import ru.pet.project.civil_project.db.entities.Resident;
import ru.pet.project.civil_project.services.dto.car.SimpleCar;

import java.util.List;

/**
 * @author Gamma on 22.01.2025
 */
public interface CarService {

    List<SimpleCar> getAll();

    SimpleCar getById(long id);

    SimpleCar add(final SimpleCar dto);

    SimpleCar update(long id, final SimpleCar dto);

    void delete(long id);

    void delete(final Resident resident);
}

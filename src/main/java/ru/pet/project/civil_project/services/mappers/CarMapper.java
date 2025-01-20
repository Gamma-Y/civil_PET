package ru.pet.project.civil_project.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.pet.project.civil_project.db.entities.Car;
import ru.pet.project.civil_project.services.dto.car.SimpleCar;

import java.util.List;

/**
 * @author Gamma on 19.01.2025
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    SimpleCar toSimpleCar(Car car);

    List<SimpleCar> toSimpleCars(List<Car> cars);
}

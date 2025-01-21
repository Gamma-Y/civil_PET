package ru.pet.project.civil_project.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.pet.project.civil_project.db.entities.Car;
import ru.pet.project.civil_project.services.dto.car.SimpleCar;

import java.util.List;

/**
 * @author Gamma on 19.01.2025
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    SimpleCar toSimpleCarDto(Car car);

    List<SimpleCar> toSimpleCarDtos(List<Car> cars);

    Car toCar(SimpleCar simpleCar);

    @Mapping(target = "id", ignore = true)
    void updateCar(SimpleCar simpleCar, @MappingTarget Car car);

}

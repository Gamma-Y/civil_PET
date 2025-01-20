package ru.pet.project.civil_project.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.pet.project.civil_project.db.entities.House;
import ru.pet.project.civil_project.services.dto.house.SimpleHouse;

import java.util.List;

/**
 * @author Gamma on 19.01.2025
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HouseMapper {
    SimpleHouse toSimpleHouse(House house);

    List<SimpleHouse> toSimpleHouseList(List<House> houses);


}

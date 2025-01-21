package ru.pet.project.civil_project.services.mappers;

import org.mapstruct.*;
import ru.pet.project.civil_project.db.entities.House;
import ru.pet.project.civil_project.services.dto.house.SimpleHouse;

import java.util.Collection;
import java.util.List;

/**
 * @author Gamma on 19.01.2025
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HouseMapper {

    SimpleHouse toSimpleHouseDto(House house);

    List<SimpleHouse> toSimpleHouseDtos(Collection<House> houses);

    House toHouse(SimpleHouse simpleHouse);

    @Mapping(target = "id", ignore = true)
    void updateHouse(SimpleHouse simpleHouse, @MappingTarget House house);


}

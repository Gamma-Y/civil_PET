package ru.pet.project.civil_project.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.pet.project.civil_project.db.entities.Resident;
import ru.pet.project.civil_project.services.dto.resident.SimpleResident;

import java.util.List;

/**
 * @author Gamma on 20.01.2025
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ResidentMapper {

    SimpleResident toSimpleResidentDto(Resident resident);

    List<SimpleResident> toSimpleResidentDtos(List<Resident> residents);

    Resident toResident(SimpleResident simpleResident);

    void updateResident(SimpleResident simpleResident, @MappingTarget Resident resident);
}

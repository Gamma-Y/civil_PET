package ru.pet.project.civil_project.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.pet.project.civil_project.db.entities.Resident;
import ru.pet.project.civil_project.services.dto.resident.SimpleResident;

import java.util.List;

/**
 * @author Gamma on 20.01.2025
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResidentMapper {

    SimpleResident toSimpleResident(Resident resident);

    List<SimpleResident> toSimpleResidents(List<Resident> residents);
}

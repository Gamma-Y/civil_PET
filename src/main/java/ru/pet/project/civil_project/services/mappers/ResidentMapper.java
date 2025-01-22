package ru.pet.project.civil_project.services.mappers;

import org.mapstruct.*;
import ru.pet.project.civil_project.db.entities.Resident;
import ru.pet.project.civil_project.services.dto.passport.SimplePassport;
import ru.pet.project.civil_project.services.dto.resident.FullResidentInfo;
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

    @Mapping(target = "passport", ignore = true)
    Resident toResident(FullResidentInfo fullResidentInfo);

    FullResidentInfo toFullResidentDto(Resident resident);

    void updateResident(SimpleResident simpleResident, @MappingTarget Resident resident);
}

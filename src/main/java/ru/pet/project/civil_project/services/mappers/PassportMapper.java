package ru.pet.project.civil_project.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.pet.project.civil_project.db.entities.Passport;
import ru.pet.project.civil_project.services.dto.passport.SimplePassport;

import java.util.List;

/**
 * @author Gamma on 20.01.2025
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PassportMapper {
    SimplePassport toSimplePassportDto(Passport passport);

    List<SimplePassport> toSimplePassportDtos(List<Passport> passports);

    Passport toPassport(SimplePassport simplePassport);

    @Mapping(target = "id", ignore = true)
    void updatePassport(SimplePassport simplePassport, @MappingTarget Passport passport);
}

package ru.pet.project.civil_project.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.pet.project.civil_project.db.entities.Passport;
import ru.pet.project.civil_project.services.dto.passport.SimplePassport;

import java.util.List;

/**
 * @author Gamma on 20.01.2025
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PassportMapper {
    SimplePassport toPassport(Passport passport);

    List<Passport> toPassports(List<Passport> passports);
}

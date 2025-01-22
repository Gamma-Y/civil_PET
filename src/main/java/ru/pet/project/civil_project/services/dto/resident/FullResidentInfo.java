package ru.pet.project.civil_project.services.dto.resident;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import ru.pet.project.civil_project.services.dto.passport.SimplePassport;

import java.io.Serializable;

/**
 * DTO for {@link ru.pet.project.civil_project.db.entities.Resident}
 */
public record FullResidentInfo(Long id, @NotEmpty @Length(min = 2, max = 100) String firstName,
                               @NotBlank @Length(min = 2, max = 100) String lastName,
                               @Length(max = 100) String patronymic,
                               @NotNull SimplePassport passport) implements Serializable {
}
package ru.pet.project.civil_project.services.dto.passport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import ru.pet.project.civil_project.db.entities.Passport;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ru.pet.project.civil_project.db.entities.Passport}
 */
public record SimplePassport(Long id, @NotNull @Positive Integer series, @NotNull @Positive Integer number,
                             @NotBlank String issuingAuthority, @NotNull LocalDate issueDate,
                             @NotEmpty @Length(min = 7, max = 7) String departmentCode, @NotNull Passport.Sex sex,
                             @NotNull LocalDate dateOfBirth, @NotBlank String placeOfBirth) implements Serializable {
}
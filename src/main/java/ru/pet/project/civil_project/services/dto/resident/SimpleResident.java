package ru.pet.project.civil_project.services.dto.resident;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link ru.pet.project.civil_project.db.entities.Resident}
 */
public record SimpleResident(Long id, @NotEmpty @Length(min = 2, max = 100) String firstName,
                             @NotBlank @Length(min = 2, max = 100) String lastName,
                             @Length(max = 100) String patronymic) implements Serializable {
}
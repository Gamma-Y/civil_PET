package ru.pet.project.civil_project.services.dto.house;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link ru.pet.project.civil_project.db.entities.House}
 */
public record SimpleHouse(Long id, @NotBlank @Length(min = 10, max = 100) String streetName) implements Serializable {
}
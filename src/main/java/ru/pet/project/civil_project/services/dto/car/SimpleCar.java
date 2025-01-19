package ru.pet.project.civil_project.services.dto.car;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link ru.pet.project.civil_project.db.entities.Car}
 */
public record SimpleCar(Long id, @NotBlank @Length(max = 50) String brand, @NotBlank @Length(max = 50) String model,
                        @NotBlank @Length(min = 5, max = 10) String carNumber) implements Serializable {
}
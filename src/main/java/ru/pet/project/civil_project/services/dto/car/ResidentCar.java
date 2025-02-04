package ru.pet.project.civil_project.services.dto.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * @author Gamma on 04.02.2025
 */
public record ResidentCar(Long id, @NotBlank @Length(max = 50) String brand, @NotBlank @Length(max = 50) String model,
                          @NotBlank @Length(min = 5, max = 10) String carNumber,
                          @NonNull @Positive Long residentId) implements Serializable {
}

package ru.pet.project.civil_project.db.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Gamma on 16.01.2025
 */

@Entity
@Table(name = "cars")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Length(max = 50)
    @NotBlank
    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Length(max = 50)
    @NotBlank
    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Length(min = 5, max = 10)
    @NotBlank
    @Column(name = "car_number", nullable = false, length = 10)
    private String carNumber;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(resident, car.resident) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(carNumber, car.carNumber);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(resident);
        result = 31 * result + Objects.hashCode(brand);
        result = 31 * result + Objects.hashCode(model);
        result = 31 * result + Objects.hashCode(carNumber);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }
}

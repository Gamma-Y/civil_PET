package ru.pet.project.civil_project.db.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Gamma on 16.01.2025
 */


@Entity
@Table(name = "residents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resident implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @NotEmpty
    @Length(min = 2, max = 100)
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 100)
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Length(max = 100)
    @Column(name = "patronymic", length = 100)
    private String patronymic;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passport_id")
    private Passport passport;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Car> cars = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "residents_houses",
            joinColumns = @JoinColumn(name = "resident_id"),
            inverseJoinColumns = @JoinColumn(name = "houses_id"))
    private Set<House> houses = new HashSet<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public void addHouse(House house) {
        houses.add(house);
    }

    public void removeHouse(House house) {
        houses.remove(house);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Resident resident = (Resident) o;
        return Objects.equals(id, resident.id) && Objects.equals(firstName, resident.firstName) && Objects.equals(lastName, resident.lastName) && Objects.equals(patronymic, resident.patronymic) && Objects.equals(passport, resident.passport) && Objects.equals(cars, resident.cars) && Objects.equals(houses, resident.houses);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);
        result = 31 * result + Objects.hashCode(patronymic);
        result = 31 * result + Objects.hashCode(passport);
        result = 31 * result + Objects.hashCode(cars);
        result = 31 * result + Objects.hashCode(houses);
        return result;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "passport=" + passport +
                ", patronymic='" + patronymic + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                '}';
    }
}

package ru.pet.project.civil_project.db.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "houses")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class House implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @ManyToMany(mappedBy = "houses")
    private Set<Resident> residents = new HashSet<>();

    @NotBlank
    @Length(min = 10, max = 100)
    @Column(name = "street_name", nullable = false, length = 100)
    private String streetName;

    public void addResident(Resident resident) {
        residents.add(resident);
    }

    public void removeResident(Resident resident) {
        residents.remove(resident);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;
        return Objects.equals(id, house.id) && Objects.equals(residents, house.residents) && Objects.equals(streetName, house.streetName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(residents);
        result = 31 * result + Objects.hashCode(streetName);
        return result;
    }

    @Override
    public String toString() {
        return "House{" +
                "streetName='" + streetName + '\'' +
                ", id=" + id +
                '}';
    }
}

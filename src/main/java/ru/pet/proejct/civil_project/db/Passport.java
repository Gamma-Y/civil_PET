package ru.pet.proejct.civil_project.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Gamma on 16.01.2025
 */

@Entity
@Table(name = "passports")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @OneToOne(mappedBy = "passport", orphanRemoval = true)
    private Resident resident;

    @NotNull
    @Size(min = 1000, max = 9999)
    @Positive
    @Column(name = "series")
    private Integer series;

    @NotNull
    @Size(min = 100000, max = 999999)
    @Positive
    @Column(name = "number")
    private Integer number;

    @NotBlank
    @Column(name = "issuing_authority", nullable = false)
    private String issuingAuthority;

    @NotNull
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @NotEmpty
    @Length(min = 7, max = 7)
    @Column(name = "department code", nullable = false, length = 7)
    private String departmentCode;

    @NotNull
    @Enumerated
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @NotNull
    @Column(name = "date of birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank
    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Passport passport = (Passport) o;
        return Objects.equals(id, passport.id) && Objects.equals(series, passport.series) && Objects.equals(number, passport.number) && Objects.equals(issuingAuthority, passport.issuingAuthority) && Objects.equals(issueDate, passport.issueDate) && Objects.equals(departmentCode, passport.departmentCode) && sex == passport.sex && Objects.equals(dateOfBirth, passport.dateOfBirth) && Objects.equals(placeOfBirth, passport.placeOfBirth);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(series);
        result = 31 * result + Objects.hashCode(number);
        result = 31 * result + Objects.hashCode(issuingAuthority);
        result = 31 * result + Objects.hashCode(issueDate);
        result = 31 * result + Objects.hashCode(departmentCode);
        result = 31 * result + Objects.hashCode(sex);
        result = 31 * result + Objects.hashCode(dateOfBirth);
        result = 31 * result + Objects.hashCode(placeOfBirth);
        return result;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "placeOfBirth='" + placeOfBirth + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex=" + sex +
                ", departmentCode='" + departmentCode + '\'' +
                ", issueDate=" + issueDate +
                ", issuingAuthority='" + issuingAuthority + '\'' +
                ", number=" + number +
                ", series=" + series +
                ", id=" + id +
                '}';
    }

    public enum Sex {
        MALE,
        FEMALE;
    }
}

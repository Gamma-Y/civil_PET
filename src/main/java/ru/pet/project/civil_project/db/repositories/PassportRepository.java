package ru.pet.project.civil_project.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.pet.project.civil_project.db.entities.Passport;

import java.util.List;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

    @Query(nativeQuery = true, value = "select p.id, p.series, p.number, p.issuing_authority, p.issue_date, p.department_code, p.sex, p.date_of_birth, p.place_of_birth, r.id, r.last_name" +
            " from passports as p inner join residents as r on r.passport_id = p.id " +
            "where r.last_name like :letter% ")
    List<Passport> findAllMaleByFirstLetterOnLastName(@Param("letter") Character letter);
}
package ru.pet.project.civil_project.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pet.project.civil_project.db.entities.Resident;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
    @Query("select distinct r from Resident r inner join r.houses houses where houses.streetName = ?1")
    List<Resident> findDistinctByHouses_StreetName(String streetName);


}
package ru.pet.project.civil_project.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet.project.civil_project.db.entities.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
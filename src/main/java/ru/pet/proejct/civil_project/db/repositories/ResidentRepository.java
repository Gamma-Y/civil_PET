package ru.pet.proejct.civil_project.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet.proejct.civil_project.db.entities.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
package ru.pet.project.civil_project.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet.project.civil_project.db.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
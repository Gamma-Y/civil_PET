package ru.pet.project.civil_project.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pet.project.civil_project.db.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
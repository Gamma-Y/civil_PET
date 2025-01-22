package ru.pet.project.civil_project.controllers.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pet.project.civil_project.exception.BadRequestException;
import ru.pet.project.civil_project.services.ResidentService;
import ru.pet.project.civil_project.services.dto.resident.SimpleResident;

import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * @author Gamma on 20.01.2025
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/residents/")
public class ResidentController {
    private static final String RESIDENT_URL = "/api/v1/residents/";


    private final ResidentService service;

    @GetMapping
    public ResponseEntity<List<SimpleResident>> getResidents() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleResident> getResident(@PathVariable @Positive long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<SimpleResident> createResident(@RequestBody @Valid final SimpleResident resident) {
        if (resident == null) {
            throw new BadRequestException("Resident object cannot be null");
        }
        SimpleResident simpleResident = service.add(resident);
        return ResponseEntity.created(URI.create(RESIDENT_URL + simpleResident.id())).body(simpleResident);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleResident> updateResident(@PathVariable long id, @RequestBody @Valid SimpleResident resident) {
        SimpleResident updateResident = service.update(id, resident);
        return ResponseEntity.ok(updateResident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleResident> deleteResident(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/streetname/{streetName}")
    public ResponseEntity<List<SimpleResident>> getAllResidentByStreetName(@PathVariable @NotBlank(message = "Street name cannot be blank") String streetName) {
        if (streetName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<SimpleResident> residents = service.findByStreetName(streetName);
        if (residents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(residents);
    }
}

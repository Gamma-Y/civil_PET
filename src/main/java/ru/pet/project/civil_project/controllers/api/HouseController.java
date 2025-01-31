package ru.pet.project.civil_project.controllers.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pet.project.civil_project.exception.BadRequestException;
import ru.pet.project.civil_project.services.HouseService;
import ru.pet.project.civil_project.services.dto.house.SimpleHouse;

import java.net.URI;
import java.util.List;

/**
 * @author Gamma on 19.01.2025
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/houses")
public class HouseController {
    private static final String HOUSE_URL = "/api/v1/houses/";

    private final HouseService houseService;

    @GetMapping
    public ResponseEntity<List<SimpleHouse>> getHouses() {
        return ResponseEntity.ok(houseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleHouse> getHouse(@PathVariable @Positive long id) {
        return ResponseEntity.ok(houseService.getById(id));
    }


    @PostMapping
    public ResponseEntity<SimpleHouse> createHouse(@RequestBody @Valid final SimpleHouse house) {
        if (house == null) {
            throw new BadRequestException("House object cannot be null");
        }
        SimpleHouse simpleHouse = houseService.add(house);
        return ResponseEntity.created(URI.create(HOUSE_URL + simpleHouse.id())).body(simpleHouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleHouse> updateHouse(@PathVariable long id, @RequestBody @Valid SimpleHouse house) {
        SimpleHouse updatedHouse = houseService.update(id, house);
        return ResponseEntity.ok(updatedHouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleHouse> deleteHouse(@PathVariable long id) {
        houseService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

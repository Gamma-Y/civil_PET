package ru.pet.project.civil_project.controllers.api;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pet.project.civil_project.services.HouseService;
import ru.pet.project.civil_project.services.dto.house.SimpleHouse;

import java.util.List;

/**
 * @author Gamma on 19.01.2025
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/houses")
public class HouseController {

    private final HouseService houseService;

    @GetMapping
    public ResponseEntity<List<SimpleHouse>> getHouses() {

        return ResponseEntity.ok(houseService.getAllAsSimple());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleHouse> getHouse(@PathVariable @Positive long id) {
        return ResponseEntity.ok(houseService.getSimpleById(id));
    }
}

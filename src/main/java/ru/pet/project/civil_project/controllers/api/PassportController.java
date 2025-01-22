package ru.pet.project.civil_project.controllers.api;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pet.project.civil_project.services.PassportService;
import ru.pet.project.civil_project.services.dto.passport.SimplePassport;

import java.util.List;

/**
 * @author Gamma on 22.01.2025
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/passports")
public class PassportController {

    private final PassportService passportService;

    @GetMapping
    public ResponseEntity<List<SimplePassport>> getPassports() {
        return ResponseEntity.ok(passportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimplePassport> getPassport(@PathVariable @Positive long id) {
        return ResponseEntity.ok(passportService.getById(id));
    }

}

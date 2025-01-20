package ru.pet.project.civil_project.controllers.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pet.project.civil_project.services.impl.ResidentServiceImpl;

/**
 * @author Gamma on 20.01.2025
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/residents/")
public class ResidentController {

    private final ResidentServiceImpl service;

}

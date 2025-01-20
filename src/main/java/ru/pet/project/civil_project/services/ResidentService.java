package ru.pet.project.civil_project.services;

import ru.pet.project.civil_project.services.dto.resident.SimpleResident;

import java.util.List;

/**
 * @author Gamma on 20.01.2025
 */
public interface ResidentService {

    SimpleResident getResident(long id);

    List<SimpleResident> getAllResidents();



}

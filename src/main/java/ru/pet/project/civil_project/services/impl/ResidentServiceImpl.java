package ru.pet.project.civil_project.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pet.project.civil_project.db.repositories.ResidentRepository;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;
import ru.pet.project.civil_project.services.ResidentService;
import ru.pet.project.civil_project.services.dto.resident.SimpleResident;
import ru.pet.project.civil_project.services.mappers.ResidentMapper;

import java.util.List;

/**
 * @author Gamma on 20.01.2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentMapper residentMapper;


    @Override
    public SimpleResident getResident(long id) {
        return residentMapper.toSimpleResident(residentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resident", id)));
    }

    @Override
    public List<SimpleResident> getAllResidents() {
        return List.of();
    }
}

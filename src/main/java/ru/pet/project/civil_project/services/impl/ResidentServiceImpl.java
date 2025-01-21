package ru.pet.project.civil_project.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.Resident;
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
    @Transactional(readOnly = true)
    public List<SimpleResident> getAllAsSimple() {
        log.info("Fetching all houses");
        List<Resident> residents = residentRepository.findAll();
        return residentMapper.toSimpleResidents(residents);
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleResident getSimpleById(long id) {
        log.info("Fetching resident with id: {}", id);
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident", id));
        return residentMapper.toSimpleResident(resident);
    }

    @Override
    @Transactional
    public SimpleResident add(SimpleResident entity) {
        log.info("Adding new resident: {}", entity);
        Resident resident = residentMapper.toResident(entity);
        resident = residentRepository.save(resident);
        return residentMapper.toSimpleResident(resident);
    }

    @Override
    @Transactional
    public SimpleResident update(long id, SimpleResident entity) {
        log.info("Updating resident with id: {}", id);
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident", id));

        residentMapper.updateResident(entity, resident);
        resident = residentRepository.save(resident);
        return residentMapper.toSimpleResident(resident);
    }

    @Override
    public void delete(long id) {
        log.info("Deleting resident with id: {}", id);
        residentRepository.findById(id)
                .ifPresentOrElse(
                        residentRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Resident", id);
                        }
                );
    }
}

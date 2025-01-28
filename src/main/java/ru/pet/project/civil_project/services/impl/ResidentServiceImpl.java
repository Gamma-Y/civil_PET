package ru.pet.project.civil_project.services.impl;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.Resident;
import ru.pet.project.civil_project.db.repositories.ResidentRepository;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;
import ru.pet.project.civil_project.services.PassportService;
import ru.pet.project.civil_project.services.ResidentService;
import ru.pet.project.civil_project.services.dto.passport.SimplePassport;
import ru.pet.project.civil_project.services.dto.resident.FullResidentInfo;
import ru.pet.project.civil_project.services.dto.resident.SimpleResident;
import ru.pet.project.civil_project.services.mappers.ResidentMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author Gamma on 20.01.2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentMapper residentMapper;
    private final PassportService passportService;


    @Override
    @Transactional(readOnly = true)
    public List<SimpleResident> getAll() {
        log.info("Fetching all residents");
        List<Resident> residents = residentRepository.findAll();
        return residentMapper.toSimpleResidentDtos(residents);
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleResident getById(long id) {
        log.info("Fetching resident with id: {}", id);
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident", id));
        return residentMapper.toSimpleResidentDto(resident);
    }

    @Override
    @Transactional
    public FullResidentInfo add(FullResidentInfo dto) {
        log.info("Adding new resident: {}", dto);
        Resident resident = residentMapper.toResident(dto);
        resident = residentRepository.save(resident);
        SimplePassport newPassport = passportService.add(resident, dto.passport());
        return residentMapper.toFullResidentDto(resident);
    }

    @Override
    @Transactional
    public SimpleResident update(long id, SimpleResident dto) {
        log.info("Updating resident with id: {}", id);
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident", id));

        residentMapper.updateResident(dto, resident);
        resident = residentRepository.save(resident);
        return residentMapper.toSimpleResidentDto(resident);
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.info("Deleting resident with id: {}", id);
        Optional<Resident> byId = residentRepository.findById(id);
        if (byId.isPresent()) {
            residentRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Resident", id);

    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleResident> findByStreetName(@NotBlank String streetName) {
        log.info("Finding all residents by street name: {}", streetName);
        List<Resident> distinctByHousesStreetName = residentRepository.findDistinctByHouses_StreetName(streetName);
        return residentMapper.toSimpleResidentDtos(distinctByHousesStreetName);
    }
}

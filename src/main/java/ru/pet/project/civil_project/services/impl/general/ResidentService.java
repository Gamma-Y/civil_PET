package ru.pet.project.civil_project.services.impl.general;

import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.Resident;
import ru.pet.project.civil_project.db.repositories.ResidentRepository;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;
import ru.pet.project.civil_project.services.dto.resident.SimpleResident;
import ru.pet.project.civil_project.services.mappers.ResidentMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author Gamma on 22.01.2025
 */

@Slf4j
@Service
public class ResidentService extends AbstractGeneralService<Resident, Long> {

    private final ResidentRepository residentRepository;
    private final ResidentMapper residentMapper;
    private final AbstractGeneralService<Resident, Long> selfService;


    protected ResidentService(ResidentRepository residentRepository, ResidentMapper residentMapper, AbstractGeneralService<Resident, Long> selfService) {
        super(residentRepository, Resident.class);
        this.residentRepository = residentRepository;
        this.residentMapper = residentMapper;
        this.selfService = selfService;
    }

    @Transactional(readOnly = true)
    public List<SimpleResident> getAll() {
        log.info("Fetching all residents");
        List<Resident> residents = selfService.findAll();
        return residentMapper.toSimpleResidentDtos(residents);
    }


    @Transactional(readOnly = true)
    public SimpleResident getById(long id) {
        log.info("Fetching resident with id: {}", id);
        Resident resident = selfService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident", id));
        return residentMapper.toSimpleResidentDto(resident);
    }


    @Transactional
    public SimpleResident add(SimpleResident dto) {
        log.info("Adding new resident: {}", dto);
        Resident resident = residentMapper.toResident(dto);
        resident = selfService.save(resident);
        return residentMapper.toSimpleResidentDto(resident);
    }


    @Transactional
    public SimpleResident update(long id, SimpleResident dto) {
        log.info("Updating resident with id: {}", id);
        Resident resident = selfService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident", id));

        residentMapper.updateResident(dto, resident);
        resident = selfService.save(resident);
        return residentMapper.toSimpleResidentDto(resident);
    }


    @Transactional
    public void delete(long id) {
        log.info("Deleting resident with id: {}", id);
        Optional<Resident> byId = selfService.findById(id);
        if (byId.isPresent()) {
            selfService.delete(id);
        } else throw new ResourceNotFoundException("Resident", id);
    }


    @Transactional(readOnly = true)
    public List<SimpleResident> findByStreetName(@NotBlank String streetName) {
        log.info("Finding all residents by street name: {}", streetName);
        List<Resident> distinctByHousesStreetName = residentRepository.findDistinctByHouses_StreetName(streetName);
        return residentMapper.toSimpleResidentDtos(distinctByHousesStreetName);
    }
}

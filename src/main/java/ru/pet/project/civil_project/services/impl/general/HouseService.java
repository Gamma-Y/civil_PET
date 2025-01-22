package ru.pet.project.civil_project.services.impl.general;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.House;
import ru.pet.project.civil_project.db.repositories.HouseRepository;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;
import ru.pet.project.civil_project.services.dto.house.SimpleHouse;
import ru.pet.project.civil_project.services.mappers.HouseMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author Gamma on 22.01.2025
 */
@Slf4j
@Service
public class HouseService extends AbstractGeneralService<House, Long> {

    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;
    private final AbstractGeneralService<House, Long> selfService;

    protected HouseService(HouseRepository houseRepository, HouseMapper houseMapper, HouseService selfService) {
        super(houseRepository, House.class);
        this.houseRepository = houseRepository;
        this.houseMapper = houseMapper;
        this.selfService = selfService;
    }

    @Transactional(readOnly = true)
    public List<SimpleHouse> getAll() {
        log.info("Fetching all houses");
        List<House> houses = selfService.findAll();
        return houseMapper.toSimpleHouseDtos(houses);
    }


    @Transactional(readOnly = true)
    public SimpleHouse getById(long id) {
        log.info("Fetching house with id: {}", id);
        House house = selfService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("House", id));
        return houseMapper.toSimpleHouseDto(house);
    }


    @Transactional
    public SimpleHouse add(SimpleHouse dto) {
        log.info("Adding new house: {}", dto);
        House house = houseMapper.toHouse(dto);
        house = selfService.save(house);
        return houseMapper.toSimpleHouseDto(house);
    }


    @Transactional
    public SimpleHouse update(long id, SimpleHouse dto) {
        log.info("Updating house with id: {}", id);
        House house = selfService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("House", id));

        houseMapper.updateHouse(dto, house);
        house = selfService.save(house);
        return houseMapper.toSimpleHouseDto(house);
    }


    @Transactional
    public void delete(long id) {
        log.info("Deleting house with id: {}", id);
        Optional<House> byId = selfService.findById(id);
        if (byId.isPresent()) {
            selfService.delete(id);
        } else throw new ResourceNotFoundException("House", id);

    }
}

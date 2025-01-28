package ru.pet.project.civil_project.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.House;
import ru.pet.project.civil_project.db.repositories.HouseRepository;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;
import ru.pet.project.civil_project.services.HouseService;
import ru.pet.project.civil_project.services.dto.house.SimpleHouse;
import ru.pet.project.civil_project.services.mappers.HouseMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author Gamma on 19.01.2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SimpleHouse> getAll() {
        log.info("Fetching all houses");
        List<House> houses = houseRepository.findAll();
        return houseMapper.toSimpleHouseDtos(houses);
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleHouse getById(long id) {
        log.info("Fetching house with id: {}", id);
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("House", id));
        return houseMapper.toSimpleHouseDto(house);
    }

    @Override
    @Transactional
    public SimpleHouse add(SimpleHouse dto) {
        log.info("Adding new house: {}", dto);
        House house = houseMapper.toHouse(dto);
        house = houseRepository.save(house);
        return houseMapper.toSimpleHouseDto(house);
    }

    @Override
    @Transactional
    public SimpleHouse update(long id, SimpleHouse dto) {
        log.info("Updating house with id: {}", id);
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("House", id));

        houseMapper.updateHouse(dto, house);
        house = houseRepository.save(house);
        return houseMapper.toSimpleHouseDto(house);
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.info("Deleting house with id: {}", id);
        Optional<House> byId = houseRepository.findById(id);
        if (byId.isPresent()) {
            houseRepository.deleteById(id);
        } else throw new ResourceNotFoundException("House", id);
    }

}

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
        return houseMapper.toSimpleHouses(houses);
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleHouse getById(long id) {
        log.info("Fetching house with id: {}", id);
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("House", id));
        return houseMapper.toSimpleHouse(house);
    }

    @Override
    @Transactional
    public SimpleHouse add(SimpleHouse simpleHouse) {
        log.info("Adding new house: {}", simpleHouse);
        House house = houseMapper.toHouse(simpleHouse);
        house = houseRepository.save(house);
        return houseMapper.toSimpleHouse(house);
    }

    @Override
    @Transactional
    public SimpleHouse update(long id, SimpleHouse simpleHouse) {
        log.info("Updating house with id: {}", id);
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("House", id));

        houseMapper.updateHouse(simpleHouse, house);
        house = houseRepository.save(house);
        return houseMapper.toSimpleHouse(house);
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.info("Deleting house with id: {}", id);
        houseRepository.findById(id)
                .ifPresentOrElse(
                        houseRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("House", id);
                        }
                );
    }

}

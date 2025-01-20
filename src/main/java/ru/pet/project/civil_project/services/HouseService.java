package ru.pet.project.civil_project.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.repositories.HouseRepository;
import ru.pet.project.civil_project.services.dto.house.SimpleHouse;
import ru.pet.project.civil_project.services.mappers.HouseMapper;

import java.util.List;

/**
 * @author Gamma on 19.01.2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;

    @Transactional(readOnly = true)
    public List<SimpleHouse> getAllAsSimple() {
        return houseMapper.toSimpleHouseList(houseRepository.findAll());
    }

    @Transactional(readOnly = true)
    public SimpleHouse getSimpleById(long id) {
        return houseMapper.toSimpleHouse(houseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("There is no house with this id: " + id)));
    }
}

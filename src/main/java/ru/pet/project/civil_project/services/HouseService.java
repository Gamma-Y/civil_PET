package ru.pet.project.civil_project.services;

import ru.pet.project.civil_project.services.dto.house.SimpleHouse;

import java.util.List;

/**
 * @author Gamma on 20.01.2025
 */
public interface HouseService {

    List<SimpleHouse> getAllAsSimple();

    SimpleHouse getSimpleById(long id);

    SimpleHouse addHouse(final SimpleHouse simpleHouse);

    SimpleHouse updateHouse(long id, final SimpleHouse simpleHouse);

    void deleteHouse(long id);
}

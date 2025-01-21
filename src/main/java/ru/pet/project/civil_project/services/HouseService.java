package ru.pet.project.civil_project.services;

import ru.pet.project.civil_project.services.dto.house.SimpleHouse;

import java.util.List;

/**
 * @author Gamma on 20.01.2025
 */
public interface HouseService {

    List<SimpleHouse> getAll();

    SimpleHouse getById(long id);

    SimpleHouse add(final SimpleHouse simpleHouse);

    SimpleHouse update(long id, final SimpleHouse simpleHouse);

    void delete(long id);
}

package ru.pet.project.civil_project.services;

import ru.pet.project.civil_project.services.dto.resident.SimpleResident;

import java.util.List;

/**
 * @author Gamma on 20.01.2025
 */
public interface ResidentService {

    List<SimpleResident> getAll();

    SimpleResident getById(long id);

    SimpleResident add(final SimpleResident entity);

    SimpleResident update(long id, final SimpleResident entity);

    void delete(long id);

}

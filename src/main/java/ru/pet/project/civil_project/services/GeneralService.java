package ru.pet.project.civil_project.services;

import java.util.List;
import java.util.Optional;

/**
 * @author Smirnov_EV6 on 22.01.2025
 */
public interface GeneralService<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    void delete(ID id);

}

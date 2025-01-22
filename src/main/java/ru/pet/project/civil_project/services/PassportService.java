package ru.pet.project.civil_project.services;

import ru.pet.project.civil_project.services.dto.passport.SimplePassport;

import java.util.List;

/**
 * @author Gamma on 21.01.2025
 */
public interface PassportService {

    List<SimplePassport> getAll();

    SimplePassport getById(long id);

//    SimplePassport add(final SimplePassport dto);
//
//    SimplePassport update(long id, final SimplePassport dto);
//
//    void delete(long id);
}

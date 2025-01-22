package ru.pet.project.civil_project.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.Passport;
import ru.pet.project.civil_project.db.repositories.PassportRepository;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;
import ru.pet.project.civil_project.services.GeneralService;
import ru.pet.project.civil_project.services.PassportService;
import ru.pet.project.civil_project.services.dto.passport.SimplePassport;
import ru.pet.project.civil_project.services.mappers.PassportMapper;

import java.util.List;

/**
 * @author Gamma on 21.01.2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final GeneralService<Passport, Long> generalService;
    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;


    @Override
    @Transactional(readOnly = true)
    public List<SimplePassport> getAll() {
        log.info("Fetching all passports");
        List<Passport> passports = generalService.findAll();
        return passportMapper.toSimplePassportDtos(passports);

    }

    @Override
    @Transactional(readOnly = true)
    public SimplePassport getById(long id) {
        log.info("Fetching passport with id: {}", id);
        Passport passport = generalService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passport", id));
        return passportMapper.toSimplePassportDto(passport);
    }

//    @Override
//    @Transactional
//    public SimplePassport add(SimplePassport dto) {
//        log.info("Adding new passport: {}", dto);
//        Passport passport = passportMapper.toPassport(dto);
//        passport = generalService.save(passport);
//        return passportMapper.toSimplePassportDto(passport);
//    }
//
//    @Override
//    @Transactional
//    public SimplePassport update(long id, SimplePassport dto) {
//        return null;
//    }
//
//    @Override
//    @Transactional
//    public void delete(long id) {
//
//    }
}

package ru.pet.project.civil_project.services.extended;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.db.entities.Passport;
import ru.pet.project.civil_project.db.entities.Resident;
import ru.pet.project.civil_project.db.repositories.PassportRepository;
import ru.pet.project.civil_project.db.repositories.ResidentRepository;
import ru.pet.project.civil_project.services.dto.resident.FullResidentInfo;
import ru.pet.project.civil_project.services.mappers.PassportMapper;
import ru.pet.project.civil_project.services.mappers.ResidentMapper;

/**
 * @author Gamma on 28.01.2025
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ResidentPassportService {
    private final ResidentRepository residentService;
    private final PassportRepository passportRepository;
    private final ResidentMapper residentMapper;
    private final PassportMapper passportMapper;


    @Transactional
    public FullResidentInfo createResident(FullResidentInfo residentDto) {
        Resident resident = residentMapper.toResident(residentDto);
        Passport passport = passportMapper.toPassport(residentDto.passport());
        resident.setPassport(passport);
        passport.setResident(resident);
        Resident save = residentService.save(resident);

        return residentMapper.toFullResidentDto(save);

    }
}

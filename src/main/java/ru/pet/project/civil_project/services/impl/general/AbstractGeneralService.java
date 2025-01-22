package ru.pet.project.civil_project.services.impl.general;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.project.civil_project.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * @author Gamma on 22.01.2025
 */
@Slf4j
public abstract class AbstractGeneralService<T, ID> implements GeneralService<T, ID> {

    private final JpaRepository<T, ID> repository;
    private final Class<T> entityClass;

    protected AbstractGeneralService(JpaRepository<T, ID> repository, Class<T> entityClass) {
        this.repository = repository;
        this.entityClass = entityClass;
    }


    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        log.debug("Try find all {}", entityClass.getSimpleName());
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        log.debug("Try find car with id: {}", id);
        return repository.findById(id);
    }

    @Override
    @Transactional
    public T save(T entity) {
        log.debug("Try save car: {}", entityClass.getSimpleName());
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        log.debug("Try delete car with id: {}", id);
        repository.findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Car", id);
                        }
                );
    }


}

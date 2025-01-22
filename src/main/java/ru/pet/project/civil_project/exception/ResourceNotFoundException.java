package ru.pet.project.civil_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Gamma on 20.01.2025
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource) {
        super("Resource not found: " + resource);
    }

    public ResourceNotFoundException(String resource, Object id) {
        super("Resource " + resource + " with id " + id + " not found");
    }
}

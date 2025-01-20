package ru.pet.project.civil_project.exception;

/**
 * @author Gamma on 20.01.2025
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

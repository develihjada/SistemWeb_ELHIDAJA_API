package com.hidajasac.hidajasac_apis.service.exeption;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.hidajasac.hidajasac_apis.util.genericResponse.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(". "));

        ApiResponse<Object> response = new ApiResponse<>(errorMessages, false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), false);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidDocumentException(InvalidDocumentException ex) {
        ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidFormatException(InvalidFormatException ex) {
        if (ex.getTargetType().isEnum()) {
            Object invalidValue = ex.getValue();
            Class<?> enumType = ex.getTargetType();
            String validValues = String.join(", ",
                    java.util.Arrays.stream(enumType.getEnumConstants())
                            .map(Object::toString)
                            .toArray(String[]::new));
            String message = String.format("Valor '%s' inválido para '%s'. Valores válidos: [%s]",
                    invalidValue, enumType.getSimpleName(), validValues);
            return ResponseEntity.badRequest().body(new ApiResponse<>(message, false));
        }
        return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getOriginalMessage(), false));
    }

    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidStateException(InvalidStateException ex) {
        ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

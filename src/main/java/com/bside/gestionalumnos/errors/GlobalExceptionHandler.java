package com.bside.gestionalumnos.errors;

import com.bside.gestionalumnos.errors.exceptions.DatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle exceptions thrown due to database errors
     * @param e - DatabaseException
     * @return ResponseEntity<ApiError> - Response entity with the error message and related entity id
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ApiError> handleSaveExceptions(DatabaseException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ApiError(e.getMessage(),e.getEntityId()));
    }

    /**
     * Handle exceptions thrown due to illegal arguments
     * @param e - IllegalArgumentException
     * @return ResponseEntity<ApiError> - Response entity with the error message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError("No se ha podido guardar la entidad provista"));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ApiError("Método no permitido por la aplicacion"));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError("Recurso no encontrado"));
    }

}

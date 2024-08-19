package com.bside.gestionalumnos.errors.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DatabaseException extends RuntimeException{

    private final String entityId;
    private final String message;
}

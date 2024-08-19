package com.bside.gestionalumnos.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String message;
    private String entityId;

    ApiError(String message) {
        this.message = message;
    }
}

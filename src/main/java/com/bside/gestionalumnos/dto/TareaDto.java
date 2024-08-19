package com.bside.gestionalumnos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TareaDto {

    @Null
    private Long idToUpdate;

    @NotNull
    private Long idAlumno;

    @NotNull
    @Size(min = 1, max = 100)
    private String nombre;
}

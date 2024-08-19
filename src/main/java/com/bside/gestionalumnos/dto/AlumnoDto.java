package com.bside.gestionalumnos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AlumnoDto {

    @Null
    private Long idToUpdate;

    @NotNull
    @Size(min = 1, max = 50)
    private String nombres;

    @NotNull
    @Size(min = 1, max = 50)
    private String apellidoPaterno;

    @NotNull
    @Size(min = 1, max = 50)
    private String apellidoMaterno;

    @Min(0)
    @Max(150)
    private Integer edad;
}

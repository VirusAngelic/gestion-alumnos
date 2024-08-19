package com.bside.gestionalumnos.repository.entity;

import com.bside.gestionalumnos.dto.AlumnoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo nombres no puede estar vacío")
    private String nombres;

    @NotBlank(message = "El campo apellido materno no puede estar vacío")
    private String apellido_materno;

    @NotBlank(message = "El campo apellido paterno no puede estar vacío")
    private String apellido_paterno;

    private Integer edad;

    public Alumno(AlumnoDto alumnoDto){
        this.nombres = alumnoDto.getNombres();
        this.apellido_materno = alumnoDto.getApellidoMaterno();
        this.apellido_paterno = alumnoDto.getApellidoPaterno();
        this.edad = alumnoDto.getEdad();
    }
}

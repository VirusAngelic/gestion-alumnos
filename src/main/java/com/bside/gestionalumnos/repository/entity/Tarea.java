package com.bside.gestionalumnos.repository.entity;

import com.bside.gestionalumnos.dto.TareaDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarea")
public class Tarea {

    public Tarea(TareaDto tarea, Alumno alumno) {
        this.nombre = tarea.getNombre();
        this.alumno = alumno;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "El campo nombre no puede estar vacío")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "alumno_id", referencedColumnName = "id")
    private Alumno alumno;
}

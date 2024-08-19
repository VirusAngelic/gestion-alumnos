package com.bside.gestionalumnos.controllers;

import com.bside.gestionalumnos.dto.AlumnoDto;
import com.bside.gestionalumnos.dto.TareaDto;
import com.bside.gestionalumnos.repository.entity.Alumno;
import com.bside.gestionalumnos.repository.entity.Tarea;
import com.bside.gestionalumnos.services.AlumnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/alumnos")
public class AlumnosController {


    private final AlumnosService alumnosService;

    public AlumnosController(AlumnosService alumnosService) {
        this.alumnosService = alumnosService;
    }

    @GetMapping("/getAlumnos")
    public HttpEntity<List<Alumno>> getAlumnos() {
        return new HttpEntity<>(alumnosService.getAllAlumnos().stream().toList());
    }

    @PostMapping("/addAlumno")
    public HttpEntity<Alumno> addAlumno(@RequestBody AlumnoDto alumno) {
        return new HttpEntity<>(alumnosService.addAlumno(alumno));
    }

    @PutMapping("/updateAlumno")
    public HttpEntity<Alumno> updateAlumno(@RequestBody AlumnoDto alumno) {
        return new HttpEntity<>(alumnosService.updateAlumno(alumno));
    }

    @DeleteMapping("/deleteAlumno")
    public HttpEntity<Alumno> deleteAlumno(@RequestParam Long id) {
        return new HttpEntity<>(alumnosService.deleteAlumno(id));
    }
}

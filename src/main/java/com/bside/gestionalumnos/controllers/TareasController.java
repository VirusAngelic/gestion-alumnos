package com.bside.gestionalumnos.controllers;

import com.bside.gestionalumnos.dto.TareaDto;
import com.bside.gestionalumnos.repository.entity.Tarea;
import com.bside.gestionalumnos.services.TareasService;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/tareas")
public class TareasController {

    private final TareasService tareasService;

    public TareasController(TareasService tareasService) {
        this.tareasService = tareasService;
    }

    @GetMapping("/getTareas")
    public HttpEntity<List<Tarea>> getTareas() {
        return new HttpEntity<>(tareasService.getAllTareas().stream().toList());
    }

    @PostMapping("/addTarea")
    public HttpEntity<Tarea> addTarea(@RequestBody TareaDto tarea) {
        return new HttpEntity<>(tareasService.addTarea(tarea));
    }

    @PutMapping("/updateTarea")
    public HttpEntity<Tarea> updateTarea(@RequestBody TareaDto tarea) {
        return new HttpEntity<>(tareasService.updateTarea(tarea));
    }

    @DeleteMapping("/deleteTarea")
    public HttpEntity<Tarea> deleteTarea(@RequestParam Long id) {
        return new HttpEntity<>(tareasService.deleteTarea(id));
    }
}

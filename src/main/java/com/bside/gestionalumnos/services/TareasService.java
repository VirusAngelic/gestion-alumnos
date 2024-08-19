package com.bside.gestionalumnos.services;

import com.bside.gestionalumnos.dto.AlumnoDto;
import com.bside.gestionalumnos.dto.TareaDto;
import com.bside.gestionalumnos.errors.exceptions.DatabaseException;
import com.bside.gestionalumnos.repository.IAlumnoRepository;
import com.bside.gestionalumnos.repository.ITareaRepository;
import com.bside.gestionalumnos.repository.entity.Alumno;
import com.bside.gestionalumnos.repository.entity.Tarea;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareasService {

    private final ITareaRepository tareaRepository;
    private final IAlumnoRepository alumnoRepository;

    public TareasService(ITareaRepository tareaRepository, IAlumnoRepository alumnoRepository) {
        this.tareaRepository = tareaRepository;
        this.alumnoRepository = alumnoRepository;
    }

    public List<Tarea> getAllTareas(){
        return tareaRepository.findAll();
    }

    public Tarea addTarea(TareaDto tarea) {
        Alumno alumnoEncontrado = alumnoRepository.findById(tarea.getIdAlumno()).orElseThrow(()-> new DatabaseException(tarea.getIdAlumno().toString(), "Id de alumno no encontrado"));
        return tareaRepository.save(new Tarea(tarea, alumnoEncontrado));
    }

    public Tarea updateTarea(TareaDto tarea) {
        Tarea tareaEncontrada = tareaRepository.findById(tarea.getIdToUpdate()).orElseThrow(()-> new DatabaseException(tarea.getIdToUpdate().toString(), "Id de tarea no encontrado"));
        Alumno alumnoEncontrado = alumnoRepository.findById(tarea.getIdAlumno()).orElseThrow(()-> new DatabaseException(tarea.getIdAlumno().toString(), "Id de alumno no encontrado"));
        tareaEncontrada.setAlumno(alumnoEncontrado);
        tareaEncontrada.setNombre(tarea.getNombre());
        return tareaRepository.save(tareaEncontrada);
    }

    public Tarea deleteTarea(Long id) {
        Tarea tareaEncontrada = tareaRepository.findById(id).orElseThrow(()-> new DatabaseException(id.toString(), "Id de tarea no encontrado"));
        tareaRepository.delete(tareaEncontrada);
        return tareaEncontrada;
    }
}

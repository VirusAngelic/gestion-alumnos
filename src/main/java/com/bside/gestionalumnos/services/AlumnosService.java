package com.bside.gestionalumnos.services;

import com.bside.gestionalumnos.dto.AlumnoDto;
import com.bside.gestionalumnos.errors.exceptions.DatabaseException;
import com.bside.gestionalumnos.repository.IAlumnoRepository;
import com.bside.gestionalumnos.repository.entity.Alumno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnosService {

    private final IAlumnoRepository alumnoRepository;

    public AlumnosService(IAlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> getAllAlumnos(){
        return alumnoRepository.findAll();
    }

    public Alumno addAlumno(AlumnoDto alumno) {
        return alumnoRepository.save(new Alumno(alumno));
    }

    public Alumno updateAlumno(AlumnoDto alumno) {
        Alumno alumnoEncontrado = alumnoRepository.findById(alumno.getIdToUpdate()).orElseThrow(()-> new DatabaseException(alumno.getIdToUpdate().toString(), "Id de alumno no encontrado"));
        alumnoEncontrado.setNombres(alumno.getNombres());
        alumnoEncontrado.setApellido_materno(alumno.getApellidoMaterno());
        alumnoEncontrado.setApellido_paterno(alumno.getApellidoPaterno());
        alumnoEncontrado.setEdad(alumno.getEdad());
        return alumnoRepository.saveAndFlush(alumnoEncontrado);
    }

    public Alumno deleteAlumno(Long id) {
        Alumno alumnoEncontrado = alumnoRepository.findById(id).orElseThrow(()-> new DatabaseException(id.toString(), "Id de alumno no encontrado"));
        alumnoRepository.delete(alumnoEncontrado);
        return alumnoEncontrado;
    }
}

package com.bside.gestionalumnos.services;

import com.bside.gestionalumnos.dto.TareaDto;
import com.bside.gestionalumnos.repository.IAlumnoRepository;
import com.bside.gestionalumnos.repository.ITareaRepository;
import com.bside.gestionalumnos.repository.entity.Alumno;
import com.bside.gestionalumnos.repository.entity.Tarea;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TareasServiceTest {
    private static AutoCloseable closeable;

    @Mock
    private ITareaRepository tareaRepository;

    @Mock
    private IAlumnoRepository alumnoRepository;

    @InjectMocks
    private TareasService tareasService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void getAllTareas() {
        Tarea tarea1 = new Tarea();
        Tarea tarea2 = new Tarea();
        List<Tarea> expectedTareas = Arrays.asList(tarea1, tarea2);
        when(tareaRepository.findAll()).thenReturn(expectedTareas);

        //Act
        List<Tarea> actualTareas = tareasService.getAllTareas();

        //Asserts
        assertEquals(expectedTareas, actualTareas);
        verify(tareaRepository, times(1)).findAll();
    }

    @Test
    void addTarea() {
        TareaDto tareaDto = new TareaDto();
        tareaDto.setIdAlumno(1L);
        Alumno alumno = new Alumno();
        Tarea tarea = new Tarea(tareaDto, alumno);
        when(alumnoRepository.findById(anyLong())).thenReturn(Optional.of(alumno));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

        // Act
        Tarea actualTarea = tareasService.addTarea(tareaDto);

        // Assert
        assertEquals(tarea, actualTarea);
        verify(alumnoRepository, times(1)).findById(anyLong());
        verify(tareaRepository, times(1)).save(any(Tarea.class));
    }

    @Test
    void updateTarea() {
        TareaDto tareaDto = new TareaDto();
        tareaDto.setIdToUpdate(1L);
        tareaDto.setIdAlumno(1L);
        Alumno alumno = new Alumno();
        Tarea tarea = new Tarea(tareaDto, alumno);
        when(tareaRepository.findById(tareaDto.getIdToUpdate())).thenReturn(Optional.of(tarea));
        when(alumnoRepository.findById(tareaDto.getIdAlumno())).thenReturn(Optional.of(alumno));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

        // Act
        Tarea actualTarea = tareasService.updateTarea(tareaDto);

        // Assert
        assertEquals(tarea, actualTarea);
        verify(tareaRepository, times(1)).findById(tareaDto.getIdToUpdate());
        verify(alumnoRepository, times(1)).findById(tareaDto.getIdAlumno());
        verify(tareaRepository, times(1)).save(any(Tarea.class));
    }

    @Test
    void updateTarea_NotFound() {
        TareaDto tareaDto = new TareaDto();
        tareaDto.setIdToUpdate(1L);
        when(tareaRepository.findById(tareaDto.getIdToUpdate())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(Exception.class, () -> tareasService.updateTarea(tareaDto));
        verify(tareaRepository, times(1)).findById(tareaDto.getIdToUpdate());
    }

    @Test
    void deleteTarea() {
        Tarea tarea = new Tarea();
        when(tareaRepository.findById(anyLong())).thenReturn(Optional.of(tarea));

        // Act
        Tarea actualTarea = tareasService.deleteTarea(1L);

        // Assert
        assertEquals(tarea, actualTarea);
        verify(tareaRepository, times(1)).findById(anyLong());
        verify(tareaRepository, times(1)).delete(any(Tarea.class));
    }
}
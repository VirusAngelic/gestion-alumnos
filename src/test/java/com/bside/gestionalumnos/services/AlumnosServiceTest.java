package com.bside.gestionalumnos.services;

import com.bside.gestionalumnos.dto.AlumnoDto;
import com.bside.gestionalumnos.errors.exceptions.DatabaseException;
import com.bside.gestionalumnos.repository.IAlumnoRepository;
import com.bside.gestionalumnos.repository.entity.Alumno;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlumnosServiceTest {
    private static AutoCloseable closeable;

    @Mock
    private IAlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnosService alumnosService;
    
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void getAllAlumnos() {
        Alumno alumno1 = new Alumno();
        Alumno alumno2 = new Alumno();
        List<Alumno> expectedAlumnos = Arrays.asList(alumno1, alumno2);
        when(alumnoRepository.findAll()).thenReturn(expectedAlumnos);

        // Act
        List<Alumno> actualAlumnos = alumnosService.getAllAlumnos();

        // Asserts
        assertEquals(expectedAlumnos, actualAlumnos);
        verify(alumnoRepository, times(1)).findAll();
    }

    @Test
    void addAlumno() {
        AlumnoDto alumnoDto = new AlumnoDto();
        Alumno alumno = new Alumno(alumnoDto);
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumno);

        // Act
        Alumno actualAlumno = alumnosService.addAlumno(alumnoDto);

        // Assert
        assertEquals(alumno, actualAlumno);
        verify(alumnoRepository, times(1)).save(any(Alumno.class));

    }

    @Test
    void updateAlumno() {
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setIdToUpdate(1L);
        Alumno alumno = new Alumno(alumnoDto);
        when(alumnoRepository.findById(alumnoDto.getIdToUpdate())).thenReturn(Optional.of(alumno));
        when(alumnoRepository.saveAndFlush(any(Alumno.class))).thenReturn(alumno);

        // Act
        Alumno actualAlumno = alumnosService.updateAlumno(alumnoDto);

        // Assert
        assertEquals(alumno, actualAlumno);
        verify(alumnoRepository, times(1)).findById(alumnoDto.getIdToUpdate());
        verify(alumnoRepository, times(1)).saveAndFlush(any(Alumno.class));

    }

    @Test
    void updateAlumno_NotFound() {
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setIdToUpdate(1L);
        when(alumnoRepository.findById(alumnoDto.getIdToUpdate())).thenReturn(Optional.empty());

        // Assert
        assertThrows(DatabaseException.class, () -> alumnosService.updateAlumno(alumnoDto));
        verify(alumnoRepository, times(1)).findById(alumnoDto.getIdToUpdate());
        verify(alumnoRepository, never()).saveAndFlush(any(Alumno.class));
    }

    @Test
    void deleteAlumno() {
        Alumno alumno = new Alumno();
        when(alumnoRepository.findById(anyLong())).thenReturn(Optional.of(alumno));

        // Act
        Alumno actualAlumno = alumnosService.deleteAlumno(1L);

        // Assert
        assertEquals(alumno, actualAlumno);
        verify(alumnoRepository, times(1)).findById(anyLong());
        verify(alumnoRepository, times(1)).delete(any(Alumno.class));
    }
}
package com.bside.gestionalumnos.repository;

import com.bside.gestionalumnos.repository.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {
}

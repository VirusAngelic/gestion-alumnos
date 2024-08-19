package com.bside.gestionalumnos.repository;

import com.bside.gestionalumnos.repository.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITareaRepository extends JpaRepository<Tarea, Long> {
}

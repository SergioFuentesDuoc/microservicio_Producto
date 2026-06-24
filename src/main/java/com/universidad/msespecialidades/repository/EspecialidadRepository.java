package com.universidad.msespecialidades.repository;

import com.universidad.msespecialidades.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

// ═══════════════════════════════════════════════════
// JpaRepository<Especialidad, Long> nos da gratis:
//   findAll(), findById(), save(), deleteById(), count()…
//
// @Query usa JPQL: se escribe sobre CLASES y ATRIBUTOS Java,
// NO sobre tablas y columnas SQL.
// ═══════════════════════════════════════════════════
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    // SQL generado: SELECT * FROM especialidades WHERE activa = 1
    @Query("SELECT e FROM Especialidad e WHERE e.activa = true")
    List<Especialidad> findAllActivas();

    // LOWER() en ambos lados → búsqueda case-insensitive
    // CONCAT('%', :nombre, '%') → patrón LIKE dinámico
    @Query("SELECT e FROM Especialidad e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Especialidad> buscarPorNombre(@Param("nombre") String nombre);
}

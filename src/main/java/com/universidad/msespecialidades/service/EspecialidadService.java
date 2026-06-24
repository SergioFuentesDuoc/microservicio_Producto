package com.universidad.msespecialidades.service;

import com.universidad.msespecialidades.model.Especialidad;
import com.universidad.msespecialidades.repository.EspecialidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// @RequiredArgsConstructor → inyección por constructor de todos los final.
// @Service     → marca esta clase como componente de capa de negocio.
@Service
@RequiredArgsConstructor
public class EspecialidadService {

    private final EspecialidadRepository especialidadRepository;

    public List<Especialidad> obtenerTodas() {
        return especialidadRepository.findAll();
    }

    // Usa la query JPQL personalizada del repository
    public List<Especialidad> obtenerActivas() {
        return especialidadRepository.findAllActivas();
    }

    public List<Especialidad> buscarPorNombre(String nombre) {
        return especialidadRepository.buscarPorNombre(nombre);
    }

    public Optional<Especialidad> obtenerPorId(Long id) {
        return especialidadRepository.findById(id);
    }

    public Especialidad guardar(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public Optional<Especialidad> actualizar(Long id, Especialidad datos) {
        return especialidadRepository.findById(id).map(e -> {
            e.setNombre(datos.getNombre());
            e.setDescripcion(datos.getDescripcion());
            e.setActiva(datos.isActiva());
            return especialidadRepository.save(e);
        });
    }

    public void eliminar(Long id) {
        especialidadRepository.deleteById(id);
    }
}

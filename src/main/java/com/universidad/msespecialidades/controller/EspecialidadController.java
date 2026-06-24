package com.universidad.msespecialidades.controller;

import com.universidad.msespecialidades.model.Especialidad;
import com.universidad.msespecialidades.service.EspecialidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController = @Controller + @ResponseBody
// @RequestMapping → prefijo base para todos los endpoints
@RestController
@RequestMapping("/api/especialidades")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    // GET /api/especialidades → todas esta es una coneccion al API
    @GetMapping
    public List<Especialidad> obtenerTodas() {
        return especialidadService.obtenerTodas();
    }

    // GET /api/especialidades/activas → query JPQL personalizada
    @GetMapping("/activas")
    public List<Especialidad> obtenerActivas() {
        return especialidadService.obtenerActivas();
    }

    // GET /api/especialidades/buscar?nombre=cir → búsqueda parcial
    @GetMapping("/buscar")
    public List<Especialidad> buscar(@RequestParam String nombre) {
        return especialidadService.buscarPorNombre(nombre);
    }

    // GET /api/especialidades/{id}
    // ResponseEntity permite devolver 200 con body o 404 vacío
    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> obtenerPorId(@PathVariable Long id) {
        return especialidadService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/especialidades → @Valid activa las validaciones del modelo
    @PostMapping
    public ResponseEntity<Especialidad> crear(@Valid @RequestBody Especialidad especialidad) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(especialidadService.guardar(especialidad));
    }

    // PUT /api/especialidades/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Especialidad datos) {
        return especialidadService.actualizar(id, datos)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/especialidades/{id} → 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        especialidadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

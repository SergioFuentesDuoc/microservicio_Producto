package com.universidad.msespecialidades.config;

import com.universidad.msespecialidades.model.Especialidad;
import com.universidad.msespecialidades.repository.EspecialidadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// ═══════════════════════════════════════════════════
// CommandLineRunner: Spring ejecuta run() UNA VEZ
// al terminar de levantar el contexto (tablas ya creadas).
//
// Verificamos count() > 0 para no duplicar datos
// si la app se reinicia con datos existentes.
// ═══════════════════════════════════════════════════
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final EspecialidadRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            log.info(">>> Especialidades ya cargadas. Se omite inicialización.");
            return;
        }
        log.info(">>> Cargando especialidades iniciales...");
        repository.save(new Especialidad(null, "Cirugía General",    "Procedimientos quirúrgicos generales", true));
        repository.save(new Especialidad(null, "Dermatología",       "Piel, pelo y uñas en animales",        true));
        repository.save(new Especialidad(null, "Cardiología",        "Diagnóstico y tratamiento del corazón",true));
        repository.save(new Especialidad(null, "Oftalmología",       "Enfermedades oculares en animales",    true));
        repository.save(new Especialidad(null, "Traumatología",      "Huesos, articulaciones, tejidos",      false));
        log.info(">>> 5 especialidades cargadas OK.");
    }
}

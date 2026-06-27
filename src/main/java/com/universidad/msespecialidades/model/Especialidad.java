package com.universidad.msespecialidades.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ═══════════════════════════════════════════════════
// @Entity  → Hibernate registra esta clase como entidad JPA.
//            Crea/actualiza la tabla 'especialidades' al arrancar.
// @Table   → nombre explícito de la tabla en MySQL.
// @Data    → Lombok genera getters, setters, equals, toString.
// ═══════════════════════════════════════════════════
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "especialidades")
public class Especialidad {

    // @Id + IDENTITY → clave primaria AUTO_INCREMENT en MySQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "Máximo 100 caracteres")
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    //crear un limite de tamaño
    @Size(max = 300)
    @Column(length = 300)
    private String descripcion;

    // true = activa en la clínica.
    // BD almacena TINYINT(1): 1 = activa, 0 = suspendida.
    @Column(nullable = false)
    private boolean activa = true;
}

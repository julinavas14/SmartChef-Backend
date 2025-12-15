package com.safa.appsmartchef.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pasos")
public class Pasos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_paso;

    @ManyToOne
    @JoinColumn(name = "id_receta")
    private Recetas id_receta;

    private Integer numero;

    private String descripcion;
}

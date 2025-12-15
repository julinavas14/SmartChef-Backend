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
@Table(name = "recetas_ingredientes")
public class RecetasIngredientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ingrediente_receta;

    @ManyToOne
    @JoinColumn(name = "id_receta")
    private Recetas id_receta;

    @ManyToOne
    @JoinColumn(name = "id_ingrediente")
    private Ingredientes id_ingrediente;

    private String cantidad;
}

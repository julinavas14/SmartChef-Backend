package com.safa.appsmartchef.modelos;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recetas")
public class Recetas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_receta;
    private String nombre;
    private String imagen;
    private String descripcion;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer favoritos = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;


}

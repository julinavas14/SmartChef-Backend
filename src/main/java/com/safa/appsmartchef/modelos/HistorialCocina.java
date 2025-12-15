package com.safa.appsmartchef.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "historial_cocina")
public class HistorialCocina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_historial;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_receta")
    private Recetas id_receta;

    private Date fecha;
}

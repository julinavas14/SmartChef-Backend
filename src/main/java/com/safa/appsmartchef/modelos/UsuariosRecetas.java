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
@Table(name = "usuarios_recetas")
public class UsuariosRecetas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuarios_recetas;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_recetas")
    private Recetas id_recetas;
}

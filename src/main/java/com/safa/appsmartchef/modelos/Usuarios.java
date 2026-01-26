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
@Table(name = "usuarios", schema = "smartchef")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    private String email;
    private String contraseña;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;
}

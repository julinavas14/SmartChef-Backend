package com.safa.appsmartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuariosDTO {
    private Integer id_usuario;
    private String nombreUsuario;
    private String email;
    private String contraseña;
    private Integer id_tipo;
    private String nombre_tipo;
}

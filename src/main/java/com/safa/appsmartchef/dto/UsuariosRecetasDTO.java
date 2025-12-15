package com.safa.appsmartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuariosRecetasDTO {
    private Integer id_usuarios_recetas;
    private UsuariosDTO id_usuario;
    private RecetasDTO id_recetas;
}

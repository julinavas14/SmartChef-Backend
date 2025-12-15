package com.safa.appsmartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialCocinaDTO {
    private UsuariosDTO usuario;
    private RecetasDTO receta;
    private String fecha;
}

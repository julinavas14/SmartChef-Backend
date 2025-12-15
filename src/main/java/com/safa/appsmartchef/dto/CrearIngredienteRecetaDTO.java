package com.safa.appsmartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrearIngredienteRecetaDTO {
    private Integer id_receta;
    private Integer id_ingrediente;
    private String cantidad;
}

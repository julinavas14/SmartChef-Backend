package com.safa.appsmartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecetasIngredientesDTO {
    private Integer id_receta;
    private String nombre_receta;
    private Integer id_ingrediente;
    private String nombre_ingrediente;
    private String cantidad;
}
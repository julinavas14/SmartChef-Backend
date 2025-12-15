package com.safa.appsmartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecetasDTO {
    private Integer id_receta;
    private String nombre;
    private String imagen;
    private String descripcion;
    private Integer id_tipo;
    private List<String> ingredientes;
}

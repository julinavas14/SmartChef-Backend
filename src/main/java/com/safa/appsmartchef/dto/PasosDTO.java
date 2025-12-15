package com.safa.appsmartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasosDTO {
    private Integer id_paso;
    private RecetasDTO id_receta;
    private Integer numero;
    private String descripcion;
}

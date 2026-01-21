package com.safa.appsmartchef.dto;

import com.safa.appsmartchef.modelos.Recetas;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RecetasDTO {
    private Integer id_receta;
    private String nombre;
    private String imagen;
    private String descripcion;
    private Integer id_tipo;
    private List<String> ingredientes;
}

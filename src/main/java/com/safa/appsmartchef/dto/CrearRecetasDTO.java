package com.safa.appsmartchef.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearRecetasDTO {
    @NotBlank(message = "El nombre de la receta no puede estar vacío")
    private String nombre;
    @NotBlank(message = "La imagen es obligatoria")
    private String imagen;
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    @NotNull(message = "El tipo de receta es obligatorio")
    private Integer idTipo;
}

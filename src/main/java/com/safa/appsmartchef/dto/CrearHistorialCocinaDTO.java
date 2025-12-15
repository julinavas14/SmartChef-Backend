package com.safa.appsmartchef.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearHistorialCocinaDTO {
    @NotNull(message = "El usuario debe de ser obligatorio")
    private Integer usuario;
    @NotNull(message = "La receta debe de ser obligatorio")
    private Integer receta;
    @NotBlank(message = "La fecha es obligatoria")
    private String fecha;
}
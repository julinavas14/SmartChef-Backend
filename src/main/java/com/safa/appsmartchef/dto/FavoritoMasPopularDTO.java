package com.safa.appsmartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoMasPopularDTO {
    private Integer usuarioId;
    private String nombreUsuario;
    private String email;

    private Integer recetaId;
    private String nombreReceta;
    private String imagenReceta;
    private String descripcionReceta;
    private String tipoReceta;

    private Long vecesGuardadaComoFavorita;
}
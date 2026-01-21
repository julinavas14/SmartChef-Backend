package com.safa.appsmartchef.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
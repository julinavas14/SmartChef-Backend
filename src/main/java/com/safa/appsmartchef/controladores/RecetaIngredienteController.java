package com.safa.appsmartchef.controladores;


import com.safa.appsmartchef.dto.FavoritoMasPopularDTO;
import com.safa.appsmartchef.servicios.IngredienteRecetaService;
import com.safa.appsmartchef.servicios.UsuariosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/estadisticas")
@AllArgsConstructor
public class RecetaIngredienteController {
    private IngredienteRecetaService ingredienteRecetaService;
    private UsuariosService usuariosService;

    @GetMapping("/ingredientes")
    public List<String> obtenerTodos(){
        return ingredienteRecetaService.findIngredientes5();
    }

    @GetMapping("/usuarioPopular")
    public ResponseEntity<List<FavoritoMasPopularDTO>> getUsuariosConRecetaMasFavorita() {
        List<FavoritoMasPopularDTO> usuarios = usuariosService.obtenerUsuariosConRecetaMasPopular();
        return ResponseEntity.ok(usuarios);
    }
}
package com.safa.appsmartchef.controladores;

import com.safa.appsmartchef.dto.CrearUsuarioDTO;
import com.safa.appsmartchef.dto.FavoritoMasPopularDTO;
import com.safa.appsmartchef.dto.UsuariosDTO;
import com.safa.appsmartchef.servicios.UsuariosService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {
    private UsuariosService usuariosService;

    @GetMapping("/all")
    public List<UsuariosDTO> obtenerTodos(){
        return usuariosService.buscarTodos();
    }

    @PostMapping("/crear")
    public String crearUsuario(@Valid @RequestBody CrearUsuarioDTO usuariosDTO){
       return  usuariosService.CrearUsuarios(usuariosDTO);
    }


}

package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.CrearUsuariosMapper;
import com.safa.appsmartchef.conversores.UsuariosMapper;
import com.safa.appsmartchef.dto.CrearUsuarioDTO;
import com.safa.appsmartchef.dto.FavoritoMasPopularDTO;
import com.safa.appsmartchef.dto.UsuariosDTO;
import com.safa.appsmartchef.excepciones.NoHayUsuariosConFavoritosException;
import com.safa.appsmartchef.excepciones.UsuarioYaExisteException;
import com.safa.appsmartchef.modelos.Usuarios;
import com.safa.appsmartchef.repositorio.UsuariosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuariosService {
    private UsuariosRepository usuariosRepository;
    private UsuariosMapper usuariosMapper;
    private CrearUsuariosMapper crearUsuariosMapper;

    public List<UsuariosDTO> buscarTodos(){
        List<Usuarios> usuarios = usuariosRepository.findAll();
        return usuariosMapper.convertirADTO(usuarios);
    }

    public String CrearUsuarios(CrearUsuarioDTO dto) {
        boolean existeNombre = usuariosRepository.existsByNombreUsuarioEqualsIgnoreCase(dto.getNombreUsuario());
        boolean existeEmail = usuariosRepository.existsByEmailEqualsIgnoreCase(dto.getEmail());

        if (existeNombre && existeEmail) {
            throw new UsuarioYaExisteException("El nombre de usuario y el correo electrónico ya están en uso.");
        } else if (existeNombre) {
            throw new UsuarioYaExisteException("El nombre de usuario '" + dto.getNombreUsuario() + "' ya está en uso.");
        } else if (existeEmail) {
            throw new UsuarioYaExisteException("El correo electrónico '" + dto.getEmail() + "' ya está registrado.");
        }

        usuariosRepository.save(crearUsuariosMapper.toEntity(dto));
        return "Usuario creado exitosamente";
    }

    public List<FavoritoMasPopularDTO> obtenerUsuariosConRecetaMasPopular() {
        List<FavoritoMasPopularDTO> resultado = usuariosRepository.findUsuariosConRecetaMasFavorita();

        if (resultado == null || resultado.isEmpty()) {
            throw new NoHayUsuariosConFavoritosException("Aún no hay usuarios con recetas favoritas.");
        }
        return resultado;
    }
}

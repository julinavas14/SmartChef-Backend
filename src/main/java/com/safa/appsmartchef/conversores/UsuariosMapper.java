package com.safa.appsmartchef.conversores;

import com.safa.appsmartchef.dto.UsuariosDTO;
import com.safa.appsmartchef.modelos.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {
    Usuarios convertirAEntity(UsuariosDTO dto);

    @Mapping(source = "tipo.id", target = "id_tipo")
    @Mapping(source = "tipo.nombre", target = "nombre_tipo")
    UsuariosDTO convertirADTO(Usuarios entity);

    List<UsuariosDTO> convertirADTO(List<Usuarios> usuarios);

    List<Usuarios> convertirAEntity(List<UsuariosDTO> usuariosDTO);

}

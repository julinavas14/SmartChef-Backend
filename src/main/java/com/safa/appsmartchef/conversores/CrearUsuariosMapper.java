package com.safa.appsmartchef.conversores;

import com.safa.appsmartchef.dto.CrearUsuarioDTO;
import com.safa.appsmartchef.modelos.Tipo;
import com.safa.appsmartchef.modelos.Usuarios;
import com.safa.appsmartchef.repositorio.TipoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CrearUsuariosMapper {
    @Autowired
    private TipoRepository tipoRepository;

    @Mapping(source = "idTipo", target = "tipo.id")
    public abstract Usuarios toEntity(CrearUsuarioDTO dto);

    public abstract CrearUsuarioDTO toDTO(Usuarios entity);

    Tipo transformasUsuario(Integer id){
        return  tipoRepository.findById(id).orElse(null);
    }

}

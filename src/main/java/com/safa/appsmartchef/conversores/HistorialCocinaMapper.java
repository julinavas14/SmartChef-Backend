package com.safa.appsmartchef.conversores;

import com.safa.appsmartchef.dto.CrearHistorialCocinaDTO;
import com.safa.appsmartchef.dto.HistorialCocinaDTO;
import com.safa.appsmartchef.modelos.HistorialCocina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuariosMapper.class, RecetasMapper.class})
public interface HistorialCocinaMapper {

    HistorialCocinaMapper INSTANCE = Mappers.getMapper(HistorialCocinaMapper.class);

    @Mapping(target = "id_usuario", source = "usuario")
    @Mapping(target = "id_receta", source = "receta")
    HistorialCocina convertirAEntidad(HistorialCocinaDTO dto);

    @Mapping(target = "usuario", source = "id_usuario")
    @Mapping(target = "receta", source = "id_receta")
    @Mapping(target = "fecha", source = "fecha", dateFormat = "dd/MM/yyyy, HH:mm")
    HistorialCocinaDTO convertirADto(HistorialCocina entity);

    @Mapping(target = "usuario", source = "id_usuario")
    @Mapping(target = "receta", source = "id_receta")
    @Mapping(target = "fecha", source = "fecha", dateFormat = "dd/MM/yyyy, HH:mm")
    List<HistorialCocinaDTO> convertirADTOLista(List<HistorialCocina> lista);

    @Mapping(target = "id_usuario", source = "usuario")
    @Mapping(target = "id_receta", source = "receta")
    List<HistorialCocina> convertirAEntidadLista(List<HistorialCocinaDTO> dto);

    @Mapping(target = "usuario", source = "id_usuario.id_usuario")
    @Mapping(target = "receta", source = "id_receta.id_receta")
    @Mapping(target = "fecha", source = "fecha", dateFormat = "dd/MM/yyyy, HH:mm")
    CrearHistorialCocinaDTO convertirDTOCrear(HistorialCocina entity);

    @Mapping(target = "id_usuario.id_usuario", source = "usuario")
    @Mapping(target = "id_receta.id_receta", source = "receta")
    HistorialCocina convertirEntityCrear(CrearHistorialCocinaDTO entity);

}

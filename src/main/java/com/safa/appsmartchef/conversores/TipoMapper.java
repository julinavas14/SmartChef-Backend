package com.safa.appsmartchef.conversores;


import com.safa.appsmartchef.dto.TipoDTO;
import com.safa.appsmartchef.modelos.Tipo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoMapper {

    Tipo convertirAEntity(TipoDTO dto);

    TipoDTO convertirADTO(Tipo entity);


}

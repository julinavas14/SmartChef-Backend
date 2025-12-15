package com.safa.appsmartchef.conversores;

import com.safa.appsmartchef.dto.PasosDTO;
import com.safa.appsmartchef.modelos.Pasos;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PasosMapper {
    Pasos convertirAEntity(PasosDTO dto);
    PasosDTO convertirADTO(Pasos entity);

}

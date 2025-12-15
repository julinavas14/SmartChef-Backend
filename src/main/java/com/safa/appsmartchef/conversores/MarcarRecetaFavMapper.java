package com.safa.appsmartchef.conversores;

import com.safa.appsmartchef.dto.MarcarRecetaFavDTO;
import com.safa.appsmartchef.modelos.Recetas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcarRecetaFavMapper {

    Recetas convertirAEntityFav(MarcarRecetaFavDTO dto);

    MarcarRecetaFavDTO convertirDTO(Recetas recetas);
}

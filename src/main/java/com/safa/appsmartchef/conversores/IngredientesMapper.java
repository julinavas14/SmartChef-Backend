package com.safa.appsmartchef.conversores;

import com.safa.appsmartchef.dto.IngredientesDTO;
import com.safa.appsmartchef.modelos.Ingredientes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientesMapper {
    Ingredientes convertirAEntity(IngredientesDTO dto);
    IngredientesDTO convertirADTO(Ingredientes entity);

}

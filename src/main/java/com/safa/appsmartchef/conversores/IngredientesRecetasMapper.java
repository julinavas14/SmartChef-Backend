package com.safa.appsmartchef.conversores;

import com.safa.appsmartchef.dto.RecetasIngredientesDTO;
import com.safa.appsmartchef.modelos.RecetasIngredientes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientesRecetasMapper{

    @Mapping(source = "id_receta", target = "id_receta.id_receta")
    @Mapping(source = "nombre_receta", target = "id_receta.nombre")
    @Mapping(source = "id_ingrediente", target = "id_ingrediente.id_ingrediente")
    @Mapping(source = "nombre_ingrediente", target = "id_ingrediente.nombre_ingrediente")
    RecetasIngredientes convertirAEntity(RecetasIngredientesDTO dto);

    @Mapping(target = "id_receta", source = "id_receta.id_receta")
    @Mapping(target = "nombre_receta", source = "id_receta.nombre")
    @Mapping(target = "id_ingrediente", source = "id_ingrediente.id_ingrediente")
    @Mapping(target = "nombre_ingrediente", source = "id_ingrediente.nombre_ingrediente")
    RecetasIngredientesDTO convertirADTO(RecetasIngredientes entity);
}

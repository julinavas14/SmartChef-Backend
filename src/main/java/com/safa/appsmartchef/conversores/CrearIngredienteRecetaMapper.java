package com.safa.appsmartchef.conversores;

import com.safa.appsmartchef.dto.CrearIngredienteRecetaDTO;
import com.safa.appsmartchef.modelos.RecetasIngredientes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CrearIngredienteRecetaMapper {

    @Mapping(source = "id_receta", target = "id_receta.id_receta")
    @Mapping(source = "id_ingrediente", target = "id_ingrediente.id_ingrediente")
    RecetasIngredientes convertirAEntidad(CrearIngredienteRecetaDTO dto);

    @Mapping(target = "id_receta", source = "id_receta.id_receta")
    @Mapping(target = "id_ingrediente", source = "id_ingrediente.id_ingrediente")
    CrearIngredienteRecetaDTO convertirADTO(RecetasIngredientes dto);

}

package com.safa.appsmartchef.conversores;

import com.safa.appsmartchef.dto.CrearRecetasDTO;
import com.safa.appsmartchef.dto.RecetasDTO;
import com.safa.appsmartchef.modelos.Recetas;
import com.safa.appsmartchef.servicios.IngredienteRecetaService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RecetasMapper {

    @Autowired
    protected IngredienteRecetaService ingredienteRecetaService;

    @Mapping(source = "id_tipo", target = "tipo.id")
    public abstract Recetas convertirAEntity(RecetasDTO dto);

    @Mapping(source = "tipo.id", target = "id_tipo")
    @Mapping(target = "ingredientes", ignore = true)
    public abstract RecetasDTO convertirADTO(Recetas entity);

    @Mapping(source = "tipo.id", target = "id_tipo")
    @Mapping(target = "ingredientes", ignore = true)
    public abstract List<RecetasDTO> convertirTodosDTO(List<Recetas> recetas);

    @Mapping(source = "id_tipo", target = "tipo.id")
    public abstract List<Recetas> convertirTodosEntity(List<RecetasDTO> recetasDTO);

    @Mapping(source = "idTipo", target = "tipo.id")
    public abstract Recetas convertirAEntity2(CrearRecetasDTO recetasDTO);

    @AfterMapping
    protected void agregarIngredientes(@MappingTarget RecetasDTO dto, Recetas entity) {
        try {
            List<String> listaIngredientes = ingredienteRecetaService.findListaCompra(entity.getId_receta());
            dto.setIngredientes(listaIngredientes);
        } catch (Exception e) {
            dto.setIngredientes(Collections.emptyList());
        }
    }

    @AfterMapping
    protected void agregarIngredientesLista(@MappingTarget List<RecetasDTO> dtos, List<Recetas> entities) {
        for (int i = 0; i < entities.size(); i++) {
            agregarIngredientes(dtos.get(i), entities.get(i));
        }
    }
}
package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.IngredientesMapper;
import com.safa.appsmartchef.dto.IngredientesDTO;
import com.safa.appsmartchef.modelos.Ingredientes;
import com.safa.appsmartchef.repositorio.IngredientesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredientesService {
    IngredientesRepository ingredientesRepository;
    IngredientesMapper ingredientesMapper;

    public List<IngredientesDTO> buscarTodos(){
        return ingredientesRepository.findAll().stream().map(ingredientesMapper::convertirADTO).collect(Collectors.toList());
    }

    public IngredientesDTO agregarIngrediente(@RequestBody IngredientesDTO dto){
        Ingredientes ingredientes = ingredientesMapper.convertirAEntity(dto);
        ingredientesRepository.save(ingredientes);
        return ingredientesMapper.convertirADTO(ingredientes);
    }
}

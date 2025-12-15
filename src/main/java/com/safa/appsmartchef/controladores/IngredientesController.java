package com.safa.appsmartchef.controladores;

import com.safa.appsmartchef.dto.CrearIngredienteRecetaDTO;
import com.safa.appsmartchef.dto.IngredientesDTO;
import com.safa.appsmartchef.servicios.IngredienteRecetaService;
import com.safa.appsmartchef.servicios.IngredientesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@AllArgsConstructor
public class IngredientesController {
    private IngredientesService ingredientesService;
    private IngredienteRecetaService ingredienteRecetaService;

    @GetMapping("/all")
    public List<IngredientesDTO> obtenerTodos(){
        return ingredientesService.buscarTodos();
    }

    @PostMapping("/crear")
    public IngredientesDTO crearIngrediente(@RequestBody IngredientesDTO dto){
        return ingredientesService.agregarIngrediente(dto);
    }

    @PostMapping("/anadir")
    public CrearIngredienteRecetaDTO añadirIngrediente(@RequestBody CrearIngredienteRecetaDTO dto){
        return ingredienteRecetaService.agregarIngrediente(dto);
    }
}

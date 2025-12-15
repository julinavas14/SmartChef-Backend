package com.safa.appsmartchef.controladores;


import com.safa.appsmartchef.dto.IngredientesDTO;
import com.safa.appsmartchef.dto.StockDTO;
import com.safa.appsmartchef.servicios.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock")
@AllArgsConstructor
public class StockController {
    StockService stockService;

    @GetMapping("/all")
    public List<StockDTO> obtenerTodos(){
        return stockService.buscarTodos();
    }
}

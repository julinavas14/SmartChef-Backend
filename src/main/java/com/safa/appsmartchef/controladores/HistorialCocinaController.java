package com.safa.appsmartchef.controladores;


import com.safa.appsmartchef.dto.CrearHistorialCocinaDTO;
import com.safa.appsmartchef.dto.HistorialCocinaDTO;
import com.safa.appsmartchef.servicios.HistorialCocinaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial-cocina")
@AllArgsConstructor
public class HistorialCocinaController {
    private HistorialCocinaService historialCocinaService;

    @GetMapping
    public List<HistorialCocinaDTO> listarHistorialCocina() {
        return historialCocinaService.buscarHistorialCocina();
    }

    @PostMapping
    public CrearHistorialCocinaDTO crearHistorialCocina(@Valid @RequestBody CrearHistorialCocinaDTO historialCocina){
        return historialCocinaService.crearHistorialCocina(historialCocina);
    }

}

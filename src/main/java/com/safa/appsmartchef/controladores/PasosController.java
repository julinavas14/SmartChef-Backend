package com.safa.appsmartchef.controladores;

import com.safa.appsmartchef.dto.PasosDTO;
import com.safa.appsmartchef.servicios.PasosService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pasos")
@AllArgsConstructor
public class PasosController {
    PasosService pasosService;

    @GetMapping("/all")
    public List<PasosDTO> obtenerTodos(){
        return pasosService.buscarTodos();
    }
}

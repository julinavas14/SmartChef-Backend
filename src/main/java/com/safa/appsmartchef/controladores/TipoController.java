package com.safa.appsmartchef.controladores;

import com.safa.appsmartchef.dto.TipoDTO;
import com.safa.appsmartchef.servicios.TipoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipo")
@AllArgsConstructor
public class TipoController {
    TipoService tipoService;

    @GetMapping("/all")
    public List<TipoDTO> obtenerTodos(){
        return tipoService.buscarTodos();
    }
}

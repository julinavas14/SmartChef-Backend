package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.PasosMapper;
import com.safa.appsmartchef.dto.PasosDTO;
import com.safa.appsmartchef.repositorio.PasosRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PasosService {
    PasosRepository pasosRepository;
    PasosMapper pasosMapper;

    public List<PasosDTO> buscarTodos(){
        return pasosRepository.findAll().stream().map(pasosMapper::convertirADTO).collect(Collectors.toList());
    }
}

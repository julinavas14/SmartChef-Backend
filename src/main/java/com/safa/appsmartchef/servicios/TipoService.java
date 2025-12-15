package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.TipoMapper;
import com.safa.appsmartchef.dto.TipoDTO;
import com.safa.appsmartchef.modelos.Tipo;
import com.safa.appsmartchef.repositorio.TipoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TipoService {
    private TipoRepository tipoRepository;
    private TipoMapper tipoMapper;

    public List<TipoDTO> buscarTodos(){
        return tipoRepository.findAll().stream().map(tipoMapper::convertirADTO).collect(Collectors.toList());
    }

    public Tipo getByNombre(String nombre){
        return tipoRepository.findTopByNombreEquals(nombre);
    }
}

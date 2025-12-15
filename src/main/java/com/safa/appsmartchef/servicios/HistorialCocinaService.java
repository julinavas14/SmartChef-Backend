package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.HistorialCocinaMapper;
import com.safa.appsmartchef.dto.CrearHistorialCocinaDTO;
import com.safa.appsmartchef.dto.HistorialCocinaDTO;
import com.safa.appsmartchef.excepciones.HistorialCocinaVacioException;
import com.safa.appsmartchef.modelos.HistorialCocina;
import com.safa.appsmartchef.repositorio.HistorialCocinaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HistorialCocinaService {
    private HistorialCocinaRepository historialCocinaRepository;
    private HistorialCocinaMapper historialCocinaMapper;

    public List<HistorialCocinaDTO> buscarHistorialCocina(){
        List<HistorialCocina> historialCocinas = historialCocinaRepository.findAll();
        if (historialCocinas.isEmpty()) {
            throw new HistorialCocinaVacioException("No tienes historial de cocina registrado aún.");
        }
        return historialCocinaMapper.convertirADTOLista(historialCocinas);
    }

    public CrearHistorialCocinaDTO crearHistorialCocina(CrearHistorialCocinaDTO crearHistorialCocinaDTO){
        HistorialCocina HC = historialCocinaMapper.convertirEntityCrear(crearHistorialCocinaDTO);
        historialCocinaRepository.save(HC);
        return historialCocinaMapper.convertirDTOCrear(HC);
    }
}

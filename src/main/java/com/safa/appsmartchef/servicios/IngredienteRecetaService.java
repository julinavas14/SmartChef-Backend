package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.CrearIngredienteRecetaMapper;
import com.safa.appsmartchef.conversores.IngredientesRecetasMapper;
import com.safa.appsmartchef.dto.CrearIngredienteRecetaDTO;
import com.safa.appsmartchef.dto.RecetasIngredientesDTO;
import com.safa.appsmartchef.excepciones.IngredienteNoEncontradoException;
import com.safa.appsmartchef.excepciones.ListaCompraVaciaException;
import com.safa.appsmartchef.excepciones.RecetaNoEncontradaException;
import com.safa.appsmartchef.modelos.RecetasIngredientes;
import com.safa.appsmartchef.repositorio.IngredientesRepository;
import com.safa.appsmartchef.repositorio.RecetasIngredientesRepository;
import com.safa.appsmartchef.repositorio.RecetasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredienteRecetaService {
    private RecetasIngredientesRepository recetasIngredientesRepository;
    private RecetasRepository recetaRepository;
    private IngredientesRepository ingredienteRepository;
    private CrearIngredienteRecetaMapper crearIngredienteRecetaMapper;

    public List<String> findIngredientes5() {
        List<Object[]> top5 = recetasIngredientesRepository.findTop5IngredientesMasUtilizados();
        if (top5 == null || top5.isEmpty()) {
            throw new IngredienteNoEncontradoException(
                    "Aún no hay suficientes recetas con ingredientes para mostrar los más populares."
            );
        }
        return top5.stream()
                .map(row -> (String) row[0] + " " + row[1])
                .collect(Collectors.toList());
    }


    public List<String> findListaCompra(int idReceta) {
        boolean recetaExiste = recetaRepository.existsById(idReceta);

        if (!recetaExiste) {
            throw new RecetaNoEncontradaException("No se encontró ninguna receta con el ID: " + idReceta);
        }

        List<Object[]> ingredientes = recetasIngredientesRepository.obtenerIngredientesDeReceta(idReceta);

        if (ingredientes == null || ingredientes.isEmpty()) {
            throw new ListaCompraVaciaException("La receta con ID " + idReceta + " no tiene ingredientes asociados. No se puede generar la lista de la compra.");
        }

        return ingredientes.stream()
                .map(row -> (String) row[0] + " " + row[1])
                .collect(Collectors.toList());
    }

    public CrearIngredienteRecetaDTO agregarIngrediente(CrearIngredienteRecetaDTO dto) {
        ingredienteRepository.findById(dto.getId_ingrediente()).orElseThrow(() -> new IngredienteNoEncontradoException("El ingrediente con ID " + dto.getId_ingrediente() + " no existe"));
        recetaRepository.findById(dto.getId_receta()).orElseThrow(() -> new RecetaNoEncontradaException("La receta con ID " + dto.getId_receta() + " no existe"));
        RecetasIngredientes ri = crearIngredienteRecetaMapper.convertirAEntidad(dto);
        recetasIngredientesRepository.save(ri);
        return crearIngredienteRecetaMapper.convertirADTO(ri);
    }
}

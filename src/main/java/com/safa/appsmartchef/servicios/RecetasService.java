package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.MarcarRecetaFavMapper;
import com.safa.appsmartchef.conversores.RecetasMapper;
import com.safa.appsmartchef.dto.CrearRecetasDTO;
import com.safa.appsmartchef.dto.MarcarRecetaFavDTO;
import com.safa.appsmartchef.dto.RecetasDTO;
import com.safa.appsmartchef.excepciones.ElementoNoEncontradoException;
import com.safa.appsmartchef.excepciones.RecetaYaExisteException;
import com.safa.appsmartchef.excepciones.YaEsFavoritaException;
import com.safa.appsmartchef.modelos.Recetas;
import com.safa.appsmartchef.modelos.Tipo;
import com.safa.appsmartchef.repositorio.RecetasRepository;
import com.safa.appsmartchef.repositorio.TipoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecetasService {
    private RecetasRepository recetasRepository;
    private TipoRepository tipoRepository;
    private RecetasMapper recetasMapper;
    private MarcarRecetaFavMapper marcarRecetaFavMapper;
    private IngredienteRecetaService ingredienteRecetaService;

    public List<RecetasDTO> buscarTodos(){
        List<Recetas> recetas = recetasRepository.findAll();
        if (recetas.isEmpty()) {
            throw new ElementoNoEncontradoException("No hay recetas disponibles en este momento");
        }
        return recetasMapper.convertirTodosDTO(recetas);
    }

    public List<RecetasDTO> obtenerPorCategoria(Integer idTipo) {
        if (idTipo < 1 || idTipo > 4) {
            throw new IllegalArgumentException("La categoría debe ser 1, 2, 3 o 4");
        }
        List<Recetas> recetas = recetasRepository.buscarPorCategoria(idTipo);
        return recetasMapper.convertirTodosDTO(recetas);
    }

    public RecetasDTO buscarPorId(Integer id_receta) {
        Recetas receta = recetasRepository.findById(id_receta)
                .orElseThrow(() -> new ElementoNoEncontradoException("Receta no encontrada"));
        return recetasMapper.convertirADTO(receta);
    }

    public RecetasDTO crearReceta(CrearRecetasDTO dto) {
        if (recetasRepository.existsByNombreIgnoreCase(dto.getNombre().trim())) {
            throw new RecetaYaExisteException("Ya existe una receta con el nombre '" + dto.getNombre() + "'");
        }
        tipoRepository.findById(dto.getIdTipo()).orElseThrow(() -> new IllegalArgumentException("Tipo de receta no encontrado con id: " + dto.getIdTipo()));

        Recetas receta = recetasMapper.convertirAEntity2(dto);
        Recetas recetaGuardada = recetasRepository.save(receta);
        return recetasMapper.convertirADTO(recetaGuardada);
    }

    public void eliminarReceta(Integer id_recetas){
        recetasRepository.deleteById(id_recetas);
    }

    public void modificarReceta(Integer id_receta, CrearRecetasDTO recetasDTO){
        Recetas receta = recetasRepository.findById(id_receta).orElse(null);

        if(receta != null){
            receta.setNombre(recetasDTO.getNombre());
            receta.setDescripcion(recetasDTO.getDescripcion());
            receta.setImagen(recetasDTO.getImagen());
            Tipo tipoEncontrado = tipoRepository.findById(recetasDTO.getIdTipo())
                    .orElseThrow(() -> new IllegalArgumentException("Tipo no encontrado con id: " + recetasDTO.getIdTipo()));
            receta.setTipo(tipoEncontrado);
            recetasRepository.save(receta);
        } else {
            throw new IllegalArgumentException("Receta no encontrada");
        }
    }

    public String marcarFavoritos(Integer id_receta){
        Recetas receta = recetasRepository.findById(id_receta)
                .orElseThrow(() -> new ElementoNoEncontradoException("Receta no encontrada"));

        if (receta.getFavoritos() == 1){
            throw new YaEsFavoritaException("Receta ya esta en favoritos");
        } else {
            receta.setFavoritos(1);
            recetasRepository.save(receta);
            marcarRecetaFavMapper.convertirDTO(receta);
            return "Puesto en favoritos";
        }
    }
}

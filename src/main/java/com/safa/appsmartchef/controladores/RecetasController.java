package com.safa.appsmartchef.controladores;

import com.safa.appsmartchef.conversores.RecetasMapper;
import com.safa.appsmartchef.dto.CrearRecetasDTO;
import com.safa.appsmartchef.dto.RecetasDTO;
import com.safa.appsmartchef.servicios.IngredienteRecetaService;
import com.safa.appsmartchef.servicios.RecetasService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8100"})
@RequestMapping("/recetas")
@AllArgsConstructor
public class RecetasController {
    private RecetasService recetasService;
    RecetasMapper recetasMapper;
    private IngredienteRecetaService  ingredienteRecetaService;

    @GetMapping("/all")
    public List<RecetasDTO> obtenerTodos(){
        return recetasService.buscarTodos();
    }

    @PostMapping("/crear")
    public RecetasDTO crearReceta(@Valid @RequestBody CrearRecetasDTO recetasDTO){
        return recetasService.crearReceta(recetasDTO);
    }

    @GetMapping("/{id_receta}")
    public RecetasDTO obtenerRecetaPorId(@Valid @PathVariable Integer id_receta) {
        return recetasService.buscarPorId(id_receta);
    }

    @DeleteMapping("/{id_receta}")
    public void eliminarReceta(@PathVariable Integer  id_receta){
        recetasService.eliminarReceta(id_receta);
    }

    @PutMapping("/modificar/{id_receta}")
    public void modificarReceta(@PathVariable  Integer id_receta, @RequestBody CrearRecetasDTO recetasDTO){
        recetasService.modificarReceta(id_receta, recetasDTO);
    }

    @GetMapping("/filtro/{idTipo}")
    public List<RecetasDTO> obtenerPorCategoria(@PathVariable Integer idTipo) {  // Cambiado a List<RecetasDTO>
        return recetasService.obtenerPorCategoria(idTipo);
    }

    @PostMapping("/{id_receta}/favorito")
    public String marcarFavoritos(@PathVariable  Integer id_receta){
        recetasService.marcarFavoritos(id_receta);
        return "Marcado como favoritos";
    }

    @PostMapping("/{id}/lista-compra")
    public List<String> listaCompra(@Valid @PathVariable  Integer id){
        ingredienteRecetaService.findListaCompra(id);
        return ingredienteRecetaService.findListaCompra(id);
    }
}
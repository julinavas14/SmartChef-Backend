package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.CrearUsuariosMapper;
import com.safa.appsmartchef.conversores.HistorialCocinaMapper;
import com.safa.appsmartchef.conversores.RecetasMapper;
import com.safa.appsmartchef.dto.*;
import com.safa.appsmartchef.modelos.*;
import com.safa.appsmartchef.repositorio.*;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// La etiqueta @Mock simula el comportamiento
@SpringBootTest
public class IntegrationTest {

    @InjectMocks
    private RecetasService recetasService;

    @InjectMocks
    private HistorialCocinaService historialCocinaService;

    @Mock
    private HistorialCocinaRepository historialCocinaRepository;

    @InjectMocks
    private IngredienteRecetaService ingredienteRecetaService;

    @Mock
    private RecetasIngredientesRepository recetasIngredientesRepository;

    @Mock
    private RecetasRepository recetasRepository;

    @InjectMocks
    private UsuariosService  usuariosService;

    @Mock
    private HistorialCocinaMapper historialCocinaMapper;

    @Mock
    private UsuariosRepository usuariosRepository;

    @Mock
    private RecetasMapper recetasMapper;


    @Mock
    private CrearUsuariosMapper usuariosMapper;

    @Mock
    private TipoRepository tipoRepository;


    @Test
    @DisplayName("Servicio 1 -> Integración")
    void registrarUsuarioTestINT(){
        Tipo tipo = new Tipo();
        tipo.setId(1);
        tipo.setNombre("vegano");

        //Creamos Usuario
        CrearUsuarioDTO nuevoUsuario = new CrearUsuarioDTO();
        nuevoUsuario.setNombreUsuario("usuario");
        nuevoUsuario.setEmail("usuario@safareyes.es");
        nuevoUsuario.setContraseña("123456");
        nuevoUsuario.setIdTipo(null);

        //Simulamos el repositorio
        Usuarios usuario = new Usuarios();
        usuario.setNombreUsuario("usuario");
        usuario.setEmail("usuario@safareyes.es");
        usuario.setContraseña("123456");
        usuario.setTipo(tipo);


        //Esto simula el comportamiento del usuario, verifica que ha creado el usuario
        when(usuariosRepository.existsByNombreUsuarioEqualsIgnoreCase("usuario")).thenReturn(false);
        when(usuariosRepository.existsByEmailEqualsIgnoreCase("usuario@safareyes.es")).thenReturn(false);
        when(usuariosMapper.toEntity(nuevoUsuario)).thenReturn(usuario);
        when(usuariosRepository.save(any(Usuarios.class))).thenReturn(usuario);

        String result = usuariosService.CrearUsuarios(nuevoUsuario);
        assertNotNull(result);

        verify(usuariosRepository).save(usuario);
    }

    @Test
    @DisplayName("Servicio 2 -> Integración")
    void registrarRecetasTestINT(){
        CrearRecetasDTO dto = new CrearRecetasDTO();
        dto.setNombre("receta");
        dto.setImagen("receta.jpg");
        dto.setDescripcion("...");
        dto.setIdTipo(1);

        RecetasDTO recetasDTO = new RecetasDTO();
        recetasDTO.setNombre("receta");
        recetasDTO.setImagen("receta.jpg");
        recetasDTO.setDescripcion("...");
        recetasDTO.setId_tipo(1);

        Recetas recetas = new Recetas();
        recetas.setNombre("receta");
        recetas.setImagen("receta.jpg");
        recetas.setDescripcion("...");
        recetas.setTipo(null);
        recetas.setFavoritos(1);

        when(recetasRepository.existsByNombreIgnoreCase(dto.getNombre())).thenReturn(false);
        when(tipoRepository.findById(dto.getIdTipo())).thenReturn(Optional.of(new Tipo()));
        when(recetasMapper.convertirAEntity2(dto)).thenReturn(recetas);
        when(recetasRepository.save(recetas)).thenReturn(recetas);
        when(recetasMapper.convertirADTO(recetas)).thenReturn(recetasDTO);

        RecetasDTO receta = recetasService.crearReceta(dto);
        assertNotNull(receta);

        verify(recetasRepository).save(recetas);
    }


    @Test
    @DisplayName("Servicio 3 -> Integración")
    void buscarPorIdTestINT(){
        //GIVEN
        when(this.recetasRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Recetas()));
        when(this.recetasMapper.convertirADTO(any(Recetas.class))).thenReturn(new RecetasDTO());

        //THEN
        this.recetasService.buscarPorId(1);

        //WHEN
        verify(this.recetasRepository).findById(Mockito.anyInt());
        verify(this.recetasMapper).convertirADTO(any());
    }

    @Test
    @DisplayName("Servicio 4 -> Integración")
    void MostrarRecetaConIdTipoCorrectoTestINT(){
        Tipo tipo = new Tipo();
        tipo.setId(1);
        tipo.setNombre("vegano");

        Recetas recetas = new Recetas();
        recetas.setId_receta(10);
        recetas.setNombre("receta");
        recetas.setImagen("receta.jpg");
        recetas.setDescripcion("...");
        recetas.setTipo(tipo);
        recetas.setFavoritos(1);

        RecetasDTO recetaDTO = new RecetasDTO();
        recetaDTO.setId_receta(10);
        recetaDTO.setNombre("receta");
        recetaDTO.setImagen("receta.jpg");
        recetaDTO.setDescripcion("...");
        recetaDTO.setId_tipo(1);

        when(this.recetasRepository.buscarPorCategoria(1)).thenReturn(List.of(recetas));
        when(this.recetasMapper.convertirTodosDTO(anyList())).thenReturn(List.of(recetaDTO));

        List<RecetasDTO> resultado = recetasService.obtenerPorCategoria(1);

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("receta", resultado.get(0).getNombre());

        verify(this.recetasRepository).buscarPorCategoria(1);
        verify(this.recetasMapper).convertirTodosDTO(anyList());
    }

    @Test
    @DisplayName("Servicio 5 -> Integración")
    void MarcarFavoritosSegunIdTestINT(){
        Tipo  tipo = new Tipo();
        tipo.setId(1);
        tipo.setNombre("vegano");

        Recetas recetas = new Recetas();
        recetas.setId_receta(10);
        recetas.setNombre("receta");
        recetas.setImagen("receta.jpg");
        recetas.setDescripcion("...");
        recetas.setTipo(tipo);
        recetas.setFavoritos(0);


        when(this.recetasRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(recetas));
        when(this.recetasRepository.save(recetas)).thenReturn(recetas);

        String lista = recetasService.marcarFavoritos(recetas.getId_receta());
        assertNotNull(lista);

        verify(recetasRepository).save(recetas);
    }

    @Test
    @DisplayName("Servicio 6 -> Integración")
    void CrearListaCompraConIdCorrectoTestINT(){
        Tipo  tipo = new Tipo();
        tipo.setId(1);
        tipo.setNombre("vegano");

        Recetas recetas = new Recetas();
        recetas.setId_receta(10);
        recetas.setNombre("receta");
        recetas.setImagen("receta.jpg");
        recetas.setDescripcion("...");
        recetas.setTipo(tipo);
        recetas.setFavoritos(1);

        Ingredientes ingredientes = new Ingredientes();
        ingredientes.setId_ingrediente(10);
        ingredientes.setNombre_ingrediente("tomate");

        RecetasIngredientes ri =  new RecetasIngredientes();
        ri.setId_ingrediente_receta(1);
        ri.setId_receta(recetas);
        ri.setId_ingrediente(ingredientes);
        ri.setCantidad("200G");


        when(this.recetasRepository.existsById(recetas.getId_receta())).thenReturn(true);
        when(this.recetasIngredientesRepository.obtenerIngredientesDeReceta(recetas.getId_receta())).thenReturn(List.of(ingredientes.getNombre_ingrediente()));

        List<String> lista = ingredienteRecetaService.findListaCompra(recetas.getId_receta());
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
        assertEquals("tomate", lista.get(0));

        verify(recetasRepository).existsById(10);
        verify(recetasIngredientesRepository).obtenerIngredientesDeReceta(10);
    }

    @Test
    @DisplayName("Servicio 7 -> Integración")
    void CrearHistorialConIDCorrectosTestINT(){
        Tipo  tipo = new Tipo();
        tipo.setId(1);
        tipo.setNombre("vegano");

        Recetas recetas = new Recetas();
        recetas.setId_receta(10);
        recetas.setNombre("receta");
        recetas.setImagen("receta.jpg");
        recetas.setDescripcion("...");
        recetas.setTipo(tipo);

        Usuarios usuario = new Usuarios();
        usuario.setNombreUsuario("usuario");
        usuario.setEmail("usuario@safareyes.es");
        usuario.setContraseña("123456");
        usuario.setTipo(tipo);

        HistorialCocina historialCocina = new HistorialCocina();
        historialCocina.setId_usuario(usuario);
        historialCocina.setId_receta(recetas);
        historialCocina.setFecha(Date.from(Instant.now()));

        CrearHistorialCocinaDTO historial = new CrearHistorialCocinaDTO();
        historial.setUsuario(usuario.getId_usuario());
        historial.setReceta(recetas.getId_receta());
        historial.setFecha("19/01/2026, 14:30");

        when(historialCocinaMapper.convertirEntityCrear(historial)).thenReturn(historialCocina);
        when(historialCocinaRepository.save(historialCocina)).thenReturn(historialCocina);
        when(historialCocinaMapper.convertirDTOCrear(historialCocina)).thenReturn(historial);

        CrearHistorialCocinaDTO resultado = historialCocinaService.crearHistorialCocina(historial);
        List<CrearHistorialCocinaDTO> lista = List.of(resultado);

        // Verificaciones
        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals(historial.getUsuario(), lista.get(0).getUsuario());
        assertEquals(historial.getReceta(), lista.get(0).getReceta());

        verify(historialCocinaRepository).save(historialCocina);
    }

    @Test
    @DisplayName("Servicio 8 -> Integración")
    void MostrarHistorialConIDCorrectosTestINT(){
        Tipo  tipo = new Tipo();
        tipo.setId(1);
        tipo.setNombre("vegano");

        Recetas recetas = new Recetas();
        recetas.setId_receta(10);
        recetas.setNombre("receta");
        recetas.setImagen("receta.jpg");
        recetas.setDescripcion("...");
        recetas.setTipo(tipo);

        Usuarios usuario = new Usuarios();
        usuario.setNombreUsuario("usuario");
        usuario.setEmail("usuario@safareyes.es");
        usuario.setContraseña("123456");
        usuario.setTipo(tipo);

        RecetasDTO recetasDTO = new RecetasDTO();
        recetasDTO.setId_receta(10);
        recetasDTO.setNombre("receta");
        recetasDTO.setImagen("receta.jpg");
        recetasDTO.setDescripcion("...");
        recetasDTO.setId_tipo(tipo.getId());

        UsuariosDTO usuarioDTO = new UsuariosDTO();
        usuarioDTO.setNombreUsuario("usuario");
        usuarioDTO.setEmail("usuario@safareyes.es");
        usuarioDTO.setContraseña("123456");
        usuarioDTO.setId_tipo(tipo.getId());

        HistorialCocinaDTO historialDTO = new HistorialCocinaDTO();
        historialDTO.setUsuario(usuarioDTO);
        historialDTO.setReceta(recetasDTO);
        historialDTO.setFecha("19/01/2026, 14:30");

        HistorialCocina historialCocina = new HistorialCocina();
        historialCocina.setId_usuario(usuario);
        historialCocina.setId_receta(recetas);
        historialCocina.setFecha(Date.from(Instant.now()));

        when(historialCocinaRepository.findAll()).thenReturn(List.of(historialCocina));
        when(historialCocinaMapper.convertirADTOLista(List.of(historialCocina))).thenReturn(List.of(historialDTO));

        List<HistorialCocinaDTO> lista = historialCocinaService.buscarHistorialCocina();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());

        verify(historialCocinaRepository).findAll();
        verify(historialCocinaMapper).convertirADTOLista(List.of(historialCocina));
    }

    @Test
    @DisplayName("Servicio 9 -> Integración")
    void MostrarLos5IngredientesMasUsadosTestINT(){
        Tipo  tipo = new Tipo();
        tipo.setId(1);
        tipo.setNombre("vegano");

        Recetas recetas = new Recetas();
        recetas.setId_receta(10);
        recetas.setNombre("receta");
        recetas.setImagen("receta.jpg");
        recetas.setDescripcion("...");
        recetas.setTipo(tipo);
        recetas.setFavoritos(1);

        Ingredientes ingredientes = new Ingredientes();
        ingredientes.setId_ingrediente(10);
        ingredientes.setNombre_ingrediente("tomate");

        RecetasIngredientes ri =  new RecetasIngredientes();
        ri.setId_ingrediente_receta(1);
        ri.setId_receta(recetas);
        ri.setId_ingrediente(ingredientes);
        ri.setCantidad("200G");

        when(recetasIngredientesRepository.findTop5IngredientesMasUtilizados()).thenReturn(List.of(ingredientes.getNombre_ingrediente()));

        List<String> lista = ingredienteRecetaService.findIngredientes5();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.contains("tomate"));

        verify(recetasIngredientesRepository).findTop5IngredientesMasUtilizados();
    }

    @Test
    @DisplayName("Servicio 10 -> Integración")
    void MostrarUsuariosConRecetasMasGuardadasTestINT(){
        Tipo  tipo = new Tipo();
        tipo.setId(1);
        tipo.setNombre("vegano");

        Recetas recetas = new Recetas();
        recetas.setId_receta(10);
        recetas.setNombre("receta");
        recetas.setImagen("receta.jpg");
        recetas.setDescripcion("...");
        recetas.setTipo(tipo);
        recetas.setFavoritos(1);

        Usuarios usuario = new Usuarios();
        usuario.setNombreUsuario("usuario");
        usuario.setEmail("usuario@safareyes.es");
        usuario.setContraseña("123456");
        usuario.setTipo(tipo);

        FavoritoMasPopularDTO favoritoMasPopularDTO = new FavoritoMasPopularDTO();
        favoritoMasPopularDTO.setRecetaId(recetas.getId_receta());
        favoritoMasPopularDTO.setUsuarioId(usuario.getId_usuario());
        favoritoMasPopularDTO.setNombreReceta("receta");
        favoritoMasPopularDTO.setImagenReceta("receta.jpg");
        favoritoMasPopularDTO.setTipoReceta("vegano");
        favoritoMasPopularDTO.setNombreUsuario(usuario.getNombreUsuario());
        favoritoMasPopularDTO.setDescripcionReceta("...");
        favoritoMasPopularDTO.setEmail(usuario.getEmail());

        when(usuariosRepository.findUsuariosConRecetaMasFavorita()).thenReturn(List.of(favoritoMasPopularDTO));

        List<FavoritoMasPopularDTO> resultado = usuariosService.obtenerUsuariosConRecetaMasPopular();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());

        verify(usuariosRepository).findUsuariosConRecetaMasFavorita();
    }


}

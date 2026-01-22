package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.CrearUsuariosMapper;
import com.safa.appsmartchef.conversores.RecetasMapper;
import com.safa.appsmartchef.dto.CrearRecetasDTO;
import com.safa.appsmartchef.dto.CrearUsuarioDTO;
import com.safa.appsmartchef.dto.RecetasDTO;
import com.safa.appsmartchef.modelos.Recetas;
import com.safa.appsmartchef.modelos.Tipo;
import com.safa.appsmartchef.modelos.Usuarios;
import com.safa.appsmartchef.repositorio.RecetasRepository;
import com.safa.appsmartchef.repositorio.TipoRepository;
import com.safa.appsmartchef.repositorio.UsuariosRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// La etiqueta @Mock simula el comportamiento
@SpringBootTest
public class IntegrationTest {

    @InjectMocks
    private RecetasService recetasService;

    @Mock
    private RecetasRepository recetasRepository;

    @InjectMocks
    private UsuariosService  usuariosService;


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

        Recetas recetas = new Recetas();
        recetas.setNombre("receta");
        recetas.setImagen("receta.jpg");
        recetas.setDescripcion("...");
        recetas.setTipo(null);
        recetas.setFavoritos(1);

        when(recetasRepository.existsByNombreIgnoreCase(dto.getNombre())).thenReturn(false);
        when(tipoRepository.findById(dto.getIdTipo())).thenReturn(Optional.of(new Tipo()));
        when(recetasRepository.findByNombre("receta")).thenReturn(Optional.of(new Recetas()));
        when(recetasMapper.convertirAEntity2(dto)).thenReturn(recetas);
        when(recetasRepository.save(recetas)).thenReturn(recetas);

        RecetasDTO receta = recetasService.crearReceta(dto);

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
        Tipo  tipo = new Tipo();
        tipo.setId(1);
        tipo.setNombre("vegano");

        CrearRecetasDTO dto = new CrearRecetasDTO();
        dto.setNombre("receta");
        dto.setImagen("receta.jpg");
        dto.setDescripcion("...");
        dto.setIdTipo(1);

        Recetas recetas = new Recetas();
        recetas.setNombre("receta");
        recetas.setImagen("receta.jpg");
        recetas.setDescripcion("...");
        recetas.setTipo(tipo);
        recetas.setFavoritos(1);

        when(this.recetasRepository.buscarPorCategoria(recetas.getTipo().getId())).thenReturn(Collections.singletonList(recetas));
        when(this.recetasMapper.convertirAEntity2(dto)).thenReturn(recetas);

        List<RecetasDTO> receta = recetasService.obtenerPorCategoria(1);

        verify(this.recetasRepository).buscarPorCategoria(dto.getIdTipo());
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


    }
}

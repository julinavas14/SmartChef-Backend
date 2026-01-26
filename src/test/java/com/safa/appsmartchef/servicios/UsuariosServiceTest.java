package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.dto.CrearRecetasDTO;
import com.safa.appsmartchef.dto.CrearUsuarioDTO;
import com.safa.appsmartchef.dto.FavoritoMasPopularDTO;
import com.safa.appsmartchef.dto.RecetasDTO;
import com.safa.appsmartchef.excepciones.NoHayUsuariosConFavoritosException;
import com.safa.appsmartchef.excepciones.UsuarioYaExisteException;
import com.safa.appsmartchef.modelos.Recetas;
import com.safa.appsmartchef.modelos.Tipo;
import com.safa.appsmartchef.modelos.Usuarios;
import com.safa.appsmartchef.modelos.UsuariosRecetas;
import com.safa.appsmartchef.repositorio.RecetasRepository;
import com.safa.appsmartchef.repositorio.TipoRepository;
import com.safa.appsmartchef.repositorio.UsuarioRecetasRepository;
import com.safa.appsmartchef.repositorio.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuariosServiceTest {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private UsuarioRecetasRepository  usuarioRecetasRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private RecetasService recetasService;

    @Autowired
    private RecetasRepository recetasRepository;

    private Tipo tipoVegano;
    private Tipo tipoProteinas;

    @BeforeAll
    void cargarDatosIniciales() {
        usuariosRepository.deleteAll();
        tipoRepository.deleteAll();
        recetasRepository.deleteAll();
        usuarioRecetasRepository.deleteAll();

        Tipo t1 = new Tipo();
        t1.setNombre("ComidaBasura");
        t1 = tipoRepository.saveAndFlush(t1);

        Tipo t2 = new Tipo();
        t2.setNombre("Postres");
        t2 = tipoRepository.saveAndFlush(t2);

        Tipo t3 = new Tipo();
        t3.setNombre("Proteínas");
        t3 = tipoRepository.saveAndFlush(t3);

        Tipo t4 = new Tipo();
        t4.setNombre("Veganos");
        t4 = tipoRepository.saveAndFlush(t4);

        tipoProteinas = t3;
        tipoVegano = t4;


        tipoVegano = new Tipo();
        tipoVegano.setNombre("Veganos");
        tipoVegano = tipoRepository.saveAndFlush(tipoVegano);
    }

    @BeforeEach
    void limpiarUsuariosAntesDeCadaTest() {
        usuariosRepository.deleteAll();

        tipoVegano = new Tipo();
        tipoVegano.setNombre("vegano");
        tipoVegano = tipoRepository.save(tipoVegano);
    }

    @Test
    @DisplayName("Servicio 1 -> Usuarios Positivo")
    void registrarUsuarioDatosCorrectosTest() {
        CrearUsuarioDTO dto = new CrearUsuarioDTO();
        dto.setNombreUsuario("julian_fit");
        dto.setEmail("julian_fit@safareyes.es");
        dto.setContraseña("Passw0rdSegura2025");
        dto.setIdTipo(tipoVegano.getId());

        String resultado = usuariosService.CrearUsuarios(dto);

        assertEquals("Usuario creado exitosamente", resultado);
        assertTrue(usuariosRepository.existsByNombreUsuarioEqualsIgnoreCase("julian_fit"));
        assertTrue(usuariosRepository.existsByEmailEqualsIgnoreCase("julian_fit@safareyes.es"));
    }

    @Test
    @DisplayName("Servicio 1 -> Usuarios Negativo")
    void registrarUsuarioEmailYaExisteTest() {
        Usuarios usuarioPreexistente = new Usuarios();
        usuarioPreexistente.setNombreUsuario("usuario_duplicado");
        usuarioPreexistente.setEmail("duplicado@safareyes.es");
        usuarioPreexistente.setContraseña("pass123");
        usuarioPreexistente.setTipo(tipoProteinas);
        usuariosRepository.saveAndFlush(usuarioPreexistente);

        CrearUsuarioDTO dto = new CrearUsuarioDTO();
        dto.setNombreUsuario("otro_nombre");
        dto.setEmail("duplicado@safareyes.es");
        dto.setContraseña("otraPass456");
        dto.setIdTipo(tipoVegano.getId());

        UsuarioYaExisteException excepcion = assertThrows(
                UsuarioYaExisteException.class,
                () -> usuariosService.CrearUsuarios(dto),
                "Debería lanzar excepción por email ya registrado"
        );

        long cantidadConEseEmail = usuariosRepository.findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase("duplicado@safareyes.es"))
                .count();
        assertEquals(1, cantidadConEseEmail, "Solo debería existir un usuario con ese email");
    }

    @Test
    @DisplayName("Servicio 10 -> Positivo")
    void MostrarUsuariosConMasRecetasGuardadasTest() {
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombreUsuario("usuario");
        nuevoUsuario.setEmail("usuario@safareyes.es");
        nuevoUsuario.setContraseña("123456");
        nuevoUsuario.setTipo(tipoVegano);
        usuariosRepository.saveAndFlush(nuevoUsuario);

        Usuarios nuevoUsuario2 = new Usuarios();
        nuevoUsuario2.setNombreUsuario("usuario2");
        nuevoUsuario2.setEmail("usuario2@safareyes.es");
        nuevoUsuario2.setContraseña("123456");
        nuevoUsuario2.setTipo(tipoVegano);
        usuariosRepository.saveAndFlush(nuevoUsuario2);

        Usuarios nuevoUsuario3 = new Usuarios();
        nuevoUsuario3.setNombreUsuario("usuario3");
        nuevoUsuario3.setEmail("usuario3@safareyes.es");
        nuevoUsuario3.setContraseña("123456");
        nuevoUsuario3.setTipo(tipoVegano);
        usuariosRepository.saveAndFlush(nuevoUsuario3);

        Recetas receta1 = new Recetas();
        receta1.setNombre("Receta B");
        receta1.setImagen("imgB.jpg");
        receta1.setDescripcion("desc B");
        receta1.setFavoritos(1);
        receta1.setTipo(tipoVegano);
        recetasRepository.saveAndFlush(receta1);

        Recetas receta2 = new Recetas();
        receta2.setNombre("Receta B");
        receta2.setImagen("imgB.jpg");
        receta2.setDescripcion("desc B");
        receta2.setFavoritos(1);
        receta2.setTipo(tipoVegano);
        recetasRepository.saveAndFlush(receta2);

        UsuariosRecetas UsuariosRecetas = new UsuariosRecetas();
        UsuariosRecetas.setId_recetas(receta1);
        UsuariosRecetas.setId_usuario(nuevoUsuario);
        usuarioRecetasRepository.saveAndFlush(UsuariosRecetas);

        UsuariosRecetas UsuariosRecetas2 = new UsuariosRecetas();
        UsuariosRecetas2.setId_recetas(receta1);
        UsuariosRecetas2.setId_usuario(nuevoUsuario2);
        usuarioRecetasRepository.saveAndFlush(UsuariosRecetas2);

        List<FavoritoMasPopularDTO> lista = usuariosRepository.findUsuariosConRecetaMasFavorita();
        assertFalse(lista.isEmpty(), "La lista esta vacía");
        assertEquals(2, lista.size(), "Deberían de aparecer 2 usuarios");
    }


    @Test
    @DisplayName("Servicio 10 -> Negativo")
    void MostrarUsuariosConRecetasGuardadasNegativoTest() {
        assertThrows(NoHayUsuariosConFavoritosException.class, () -> usuariosService.obtenerUsuariosConRecetaMasPopular());
    }

}
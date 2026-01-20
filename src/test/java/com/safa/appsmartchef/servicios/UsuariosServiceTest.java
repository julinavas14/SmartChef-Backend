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
    }

    @BeforeEach
    void limpiarUsuariosAntesDeCadaTest() {
        usuariosRepository.deleteAll();
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

        assertEquals("Usuario creado exitosamente", resultado,
                "El servicio debería devolver mensaje de éxito");

        boolean existePorNombre = usuariosRepository.existsByNombreUsuarioEqualsIgnoreCase("julian_fit");
        assertTrue(existePorNombre, "El usuario debería haberse guardado correctamente");

        boolean existePorEmail = usuariosRepository.existsByEmailEqualsIgnoreCase("julian_fit@safareyes.es");
        assertTrue(existePorEmail, "El email debería estar registrado");
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

        assertTrue(
                excepcion.getMessage().contains("correo electrónico") ||
                        excepcion.getMessage().contains("ya está registrado"),
                "El mensaje debería indicar que el email ya existe"
        );

        long cantidadConEseEmail = usuariosRepository.findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase("duplicado@safareyes.es"))
                .count();
        assertEquals(1, cantidadConEseEmail, "Solo debería existir un usuario con ese email");
    }

}
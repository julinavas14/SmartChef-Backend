package com.safa.appsmartchef.servicios;


import com.safa.appsmartchef.dto.*;
import com.safa.appsmartchef.excepciones.HistorialCocinaVacioException;
import com.safa.appsmartchef.excepciones.IngredienteNoEncontradoException;
import com.safa.appsmartchef.excepciones.RecetaNoEncontradaException;
import com.safa.appsmartchef.modelos.Ingredientes;
import com.safa.appsmartchef.modelos.RecetasIngredientes;
import com.safa.appsmartchef.modelos.Tipo;
import com.safa.appsmartchef.modelos.Usuarios;
import com.safa.appsmartchef.repositorio.*;
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
public class IngredienteRecetaServiceTest {
    @Autowired
    private RecetasService recetasService;

    @Autowired
    private IngredienteRecetaService ingredienteService;

    @Autowired
    private HistorialCocinaService historialService;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private RecetasRepository recetasRepo;

    @Autowired
    private IngredientesRepository ingredientesRepo;

    @Autowired
    private RecetasIngredientesRepository riRepo;

    @Autowired
    private TipoRepository tipoRepo;

    @Autowired
    private UsuariosRepository usuariosRepo;

    @Autowired
    private HistorialCocinaRepository historialRepo;

    private Tipo tipoVegano;

    @BeforeEach
    void setupTipos() {
        tipoVegano = tipoRepo.findTopByNombreEquals("Vegano");
        if (tipoVegano == null) {
            tipoVegano = new Tipo();
            tipoVegano.setNombre("Vegano");
            tipoVegano = tipoRepo.saveAndFlush(tipoVegano);
        }
    }

    @Test
    @DisplayName("Servicio 6 - Positivo")
    void CrearListaCompraTest() {
        CrearRecetasDTO dtoReceta = new CrearRecetasDTO("Gazpacho andaluz", "gazpacho.jpg", "Frío y rico", tipoVegano.getId());
        RecetasDTO receta = recetasService.crearReceta(dtoReceta);

        Ingredientes tomate = new Ingredientes();
        tomate.setNombre_ingrediente("Tomate pera");
        tomate = ingredientesRepo.saveAndFlush(tomate);

        RecetasIngredientes relacion = new RecetasIngredientes();
        relacion.setId_receta(recetasRepo.getReferenceById(receta.getId_receta()));
        relacion.setId_ingrediente(tomate);
        relacion.setCantidad("1 kg");
        riRepo.saveAndFlush(relacion);

        List<String> listaCompra = ingredienteService.findListaCompra(receta.getId_receta());
        assertNotNull(listaCompra);
        assertFalse(listaCompra.isEmpty());
        assertTrue(listaCompra.stream().anyMatch(s -> s.contains("Tomate pera")));
        assertTrue(listaCompra.stream().anyMatch(s -> s.contains("1 kg")));
    }

    @Test
    @DisplayName("Servicio 6 - Negativo")
    void CrearListaCompraTestNegativo() {
        assertThrows(RecetaNoEncontradaException.class,
                () -> ingredienteService.findListaCompra(999999));
    }

    @Test
    @DisplayName("Servicio 7 - Positivo")
    void CrearHistorialCocinaTest() {
        CrearUsuarioDTO dtoUser = new CrearUsuarioDTO("juan_test", "juan@test.es", "1234", tipoVegano.getId());
        usuariosService.CrearUsuarios(dtoUser);
        Usuarios usuario = usuariosRepo.findAll().stream()
                .filter(u -> u.getNombreUsuario().equals("juan_test"))
                .findFirst().orElseThrow();

        CrearRecetasDTO dtoRec = new CrearRecetasDTO("Tortilla de patatas vegana", "tortilla.jpg", "...", tipoVegano.getId());
        RecetasDTO receta = recetasService.crearReceta(dtoRec);

        CrearHistorialCocinaDTO dtoHist = new CrearHistorialCocinaDTO();
        dtoHist.setUsuario(usuario.getId_usuario());
        dtoHist.setReceta(receta.getId_receta());
        dtoHist.setFecha("19/01/2026, 14:30");

        CrearHistorialCocinaDTO resultado = historialService.crearHistorialCocina(dtoHist);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Servicio 7 -> Negativo")
    void CrearHistorialCocinaTestNegativo() {
        CrearUsuarioDTO dtoUsuario = new CrearUsuarioDTO("test_neg", "neg@test.es", "1234", tipoVegano.getId());
        usuariosService.CrearUsuarios(dtoUsuario);

        Usuarios usuario = usuariosRepo.findAll().stream()
                .filter(u -> u.getNombreUsuario().equals("test_neg"))
                .findFirst().orElseThrow();

        CrearHistorialCocinaDTO dtoHistorial = new CrearHistorialCocinaDTO();
        dtoHistorial.setUsuario(usuario.getId_usuario());
        dtoHistorial.setReceta(999999);
        dtoHistorial.setFecha("19-01-2026 14:30");

        assertThrows(Exception.class, () -> historialService.crearHistorialCocina(dtoHistorial));
    }

    @Test
    @DisplayName("Servicio 8 -> Positivo")
    void MostrarHistorialCocinaTest() {
        CrearUsuarioDTO dtoU = new CrearUsuarioDTO("mostrar_pos", "mostrar@test.es", "pass", tipoVegano.getId());
        usuariosService.CrearUsuarios(dtoU);
        Usuarios u = usuariosRepo.findAll().stream().filter(us -> us.getNombreUsuario().equals("mostrar_pos")).findFirst().get();

        CrearRecetasDTO dtoR = new CrearRecetasDTO("Sopa test", "sopa.jpg", "...", tipoVegano.getId());
        RecetasDTO r = recetasService.crearReceta(dtoR);

        CrearHistorialCocinaDTO dtoH = new CrearHistorialCocinaDTO();
        dtoH.setUsuario(u.getId_usuario());
        dtoH.setReceta(r.getId_receta());
        dtoH.setFecha("19/01/2026, 15:00");
        historialService.crearHistorialCocina(dtoH);

        List<HistorialCocinaDTO> historiales = historialService.buscarHistorialCocina();

        assertFalse(historiales.isEmpty());
        assertEquals(1, historiales.size());
    }

    @Test
    @DisplayName("Servicio 8 -> Negativo")
    void MostrarHistorialCocinaTestNegativo() {
        assertThrows(HistorialCocinaVacioException.class,
                () -> historialService.buscarHistorialCocina());
    }


    @Test
    @DisplayName("Servicio 9 -> Positivo")
    void top5IngredientesPositivoTest() {
        CrearRecetasDTO dtoR = new CrearRecetasDTO("Receta top", "top.jpg", "...", tipoVegano.getId());
        RecetasDTO receta = recetasService.crearReceta(dtoR);

        Ingredientes ing1 = new Ingredientes(null, "Tomate");
        ing1 = ingredientesRepo.saveAndFlush(ing1);

        for (int i = 0; i < 3; i++) {
            RecetasIngredientes ri = new RecetasIngredientes();
            ri.setId_receta(recetasRepo.getReferenceById(receta.getId_receta()));
            ri.setId_ingrediente(ing1);
            ri.setCantidad("200 g");
            riRepo.saveAndFlush(ri);
        }

        List<String> top5 = ingredienteService.findIngredientes5();

        assertFalse(top5.isEmpty());
        assertTrue(top5.get(0).contains("Tomate"));
    }

    @Test
    @DisplayName("Servicio 9 -> Negativo")
    void top5IngredientesNegativoTest() {
        assertThrows(IngredienteNoEncontradoException.class,
                () -> ingredienteService.findIngredientes5());
    }
}
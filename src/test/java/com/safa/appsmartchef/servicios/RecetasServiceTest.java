package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.dto.CrearRecetasDTO;
import com.safa.appsmartchef.dto.RecetasDTO;
import com.safa.appsmartchef.excepciones.ElementoNoEncontradoException;
import com.safa.appsmartchef.modelos.Recetas;
import com.safa.appsmartchef.modelos.Tipo;
import com.safa.appsmartchef.repositorio.RecetasRepository;
import com.safa.appsmartchef.repositorio.TipoRepository;
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
public class RecetasServiceTest {

    @Autowired
    private RecetasService recetasService;

    @Autowired
    private RecetasRepository recetasRepository;

    @Autowired
    private TipoRepository tipoRepository;

    private Tipo tipoValido;

    @BeforeAll
    void cargarDatosIniciales() {
        recetasRepository.deleteAll();
        tipoRepository.deleteAll();

        tipoValido = new Tipo();
        tipoValido.setNombre("Veganos");
        tipoValido = tipoRepository.saveAndFlush(tipoValido);
    }

    @BeforeEach
    void limpiarRecetasAntesDeCadaTest() {
        recetasRepository.deleteAll();
    }

    @Test
    @DisplayName("Servicio 2 -> Positivo")
    public void testRegistrarReceta() {
        CrearRecetasDTO dto = new CrearRecetasDTO();
        dto.setNombre("Receta Test");
        dto.setDescripcion("Descripción de prueba");
        dto.setImagen("imagen.jpg");
        dto.setIdTipo(tipoValido.getId());

        RecetasDTO result = recetasService.crearReceta(dto);

        assertNotNull(result, "La receta creada no debería ser nula");
        assertEquals("Receta Test", result.getNombre(), "El nombre no coincide");

        assertTrue(recetasRepository.existsByNombreIgnoreCase("Receta Test"));
    }

    @Test
    @DisplayName("Servicio 2 -> Negativo")
    public void RegistrarRecetaTipoInvalidoTest() {
        CrearRecetasDTO dto = new CrearRecetasDTO();
        dto.setNombre("Receta Inválida");
        dto.setDescripcion("No debería crearse");
        dto.setImagen("imagen.jpg");
        dto.setIdTipo(9999);

        assertThrows(IllegalArgumentException.class,
                () -> recetasService.crearReceta(dto),
                "Debería lanzar excepción por tipo no encontrado");

        assertFalse(recetasRepository.existsByNombreIgnoreCase("Receta Inválida"));
    }

    @Test
    @DisplayName("Servicio 3 -> Positivo")
    public void MostrarRecetasTest() {
        CrearRecetasDTO dto1 = new CrearRecetasDTO("Receta A", "imgA.jpg", "desc A", tipoValido.getId());
        CrearRecetasDTO dto2 = new CrearRecetasDTO("Receta B", "imgB.jpg", "desc B", tipoValido.getId());

        recetasService.crearReceta(dto1);
        recetasService.crearReceta(dto2);

        List<RecetasDTO> result = recetasService.buscarTodos();

        assertNotNull(result, "La lista no debería ser nula");
        assertEquals(2, result.size(), "Deberían devolverse 2 recetas");
        assertTrue(result.stream().anyMatch(r -> r.getNombre().equals("Receta A")));
    }

    @Test
    @DisplayName("Servicio 3 -> Negativo")
    public void MostrarRecetaIdInvalidoTest() {
        assertThrows(ElementoNoEncontradoException.class,
                () -> recetasService.buscarPorId(9999),
                "Debería lanzar ElementoNoEncontradoException");
    }

    @Test
    @DisplayName("Servicio 4 -> Positivo")
    public void MostrarRecetaPorIdTest() {
        CrearRecetasDTO dto = new CrearRecetasDTO("Receta Visible", "img.jpg", "desc", tipoValido.getId());
        RecetasDTO receta = recetasService.crearReceta(dto);
        Integer idCreado = receta.getId_receta();

        RecetasDTO result = recetasService.buscarPorId(idCreado);

        assertNotNull(result, "La receta debería encontrarse");
        assertEquals("Receta Visible", result.getNombre(), "El nombre no coincide");
    }

    @Test
    @DisplayName("Servicio 4 -> Negativo")
    public void MostrarRecetaPorIdInvalidoTest() {
        assertThrows(ElementoNoEncontradoException.class,
                () -> recetasService.buscarPorId(8888),
                "Debería lanzar excepción por ID no encontrado");
    }

    @Test
    @DisplayName("Servicio 5 -> Positivo")
    public void MarcarFavoritoTest() {
        CrearRecetasDTO dto = new CrearRecetasDTO("Receta Favorita", "fav.jpg", "desc fav", tipoValido.getId());
        RecetasDTO receta = recetasService.crearReceta(dto);
        Integer id = receta.getId_receta();

        String resultado = recetasService.marcarFavoritos(id);

        assertEquals("Puesto en favoritos", resultado, "El mensaje no es el esperado");

        Recetas actualizada = recetasRepository.findById(id).orElseThrow();
        assertEquals(1, actualizada.getFavoritos(), "El campo favoritos debería ser 1");
    }

    @Test
    @DisplayName("Servicio 5 -> Negativo")
    public void MarcarFavoritoIdInvalidoTest() {
        assertThrows(ElementoNoEncontradoException.class,
                () -> recetasService.marcarFavoritos(7777),
                "Debería lanzar excepción por receta no encontrada");
    }
}
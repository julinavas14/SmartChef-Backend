package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.RecetasMapper;
import com.safa.appsmartchef.dto.RecetasDTO;
import com.safa.appsmartchef.modelos.Recetas;
import com.safa.appsmartchef.repositorio.RecetasRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

// La etiqueta @Mock simula el comportamiento
@SpringBootTest
public class RecetasServiceIntegrationTest {

    @InjectMocks
    private RecetasService recetasService;

    @Mock
    private RecetasRepository recetasRepository;

    @Mock
    private RecetasMapper recetasMapper;



    @Test
    @DisplayName("Servicio 3 - Integración")
    void buscarPorIdIntegration(){
        //GIVEN
        Mockito.when(this.recetasRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Recetas()));
        Mockito.when(this.recetasMapper.convertirADTO(Mockito.any(Recetas.class))).thenReturn(new RecetasDTO());

        //THEN
        this.recetasService.buscarPorId(1);

        //WHEN
        Mockito.verify(this.recetasRepository).findById(Mockito.anyInt());
        Mockito.verify(this.recetasMapper).convertirADTO(Mockito.any());
    }
}

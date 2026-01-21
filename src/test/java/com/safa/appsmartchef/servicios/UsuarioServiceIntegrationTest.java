package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.UsuariosMapper;
import com.safa.appsmartchef.repositorio.UsuariosRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioServiceIntegrationTest {

    @InjectMocks
    private UsuariosService usuariosService;

    @Mock
    private UsuariosRepository usuariosRepository;

    @Mock
    private UsuariosMapper usuariosMapper;

    @Test
    @DisplayName("Servicio 1 - Integración")
    void buscarPorIdIntegration(){
        //GIVEN

        //THEN

        //WHEN
    }
}

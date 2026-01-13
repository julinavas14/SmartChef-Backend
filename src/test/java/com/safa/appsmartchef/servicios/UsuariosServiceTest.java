package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.modelos.Tipo;
import com.safa.appsmartchef.modelos.Usuarios;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuariosServiceTest {
    @Autowired
    private UsuariosService usuariosService;

    Usuarios user = new Usuarios();
    Tipo tipo = new Tipo();

    //@BeforeAll
    //void setUp() {
      //  user.setNombreUsuario("Julián");
        //user.setEmail("jnavasmedina@safareyes.es");
       // user.setContraseña("contraseña12345");
        //user.setTipo(tipo.);
    //}

    //@Test
    //public void buscarTodosTest(){


    //}
}

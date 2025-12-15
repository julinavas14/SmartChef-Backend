package com.safa.appsmartchef.repositorio;

import com.safa.appsmartchef.modelos.UsuariosRecetas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRecetasRepository extends JpaRepository<UsuariosRecetas, Integer> {
}

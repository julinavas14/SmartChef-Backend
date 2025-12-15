package com.safa.appsmartchef.repositorio;

import com.safa.appsmartchef.modelos.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    Tipo findTopByNombreEquals(String nombre);
}

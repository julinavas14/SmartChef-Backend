package com.safa.appsmartchef.repositorio;

import com.safa.appsmartchef.modelos.HistorialCocina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialCocinaRepository extends JpaRepository<HistorialCocina, Integer> {

}

package com.safa.appsmartchef.repositorio;

import com.safa.appsmartchef.modelos.Pasos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasosRepository extends JpaRepository<Pasos, Integer> {
}

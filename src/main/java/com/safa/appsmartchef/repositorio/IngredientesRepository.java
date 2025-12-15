package com.safa.appsmartchef.repositorio;

import com.safa.appsmartchef.modelos.Ingredientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientesRepository extends JpaRepository<Ingredientes, Integer> {
}

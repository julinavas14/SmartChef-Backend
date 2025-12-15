package com.safa.appsmartchef.repositorio;

import com.safa.appsmartchef.modelos.StockIngredientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockIngredientesRepository extends JpaRepository<StockIngredientes, Integer> {
}

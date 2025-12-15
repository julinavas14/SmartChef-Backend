package com.safa.appsmartchef.repositorio;

import com.safa.appsmartchef.modelos.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
}

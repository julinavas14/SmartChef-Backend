package com.safa.appsmartchef.repositorio;

import com.safa.appsmartchef.modelos.Recetas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetasRepository extends JpaRepository<Recetas, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);

    @Query("SELECT r FROM Recetas r WHERE r.tipo.id = :idTipo")
    List<Recetas> buscarPorCategoria(@Param("idTipo") Integer idTipo);

}

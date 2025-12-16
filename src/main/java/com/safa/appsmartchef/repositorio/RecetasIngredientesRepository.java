package com.safa.appsmartchef.repositorio;

import com.safa.appsmartchef.modelos.RecetasIngredientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecetasIngredientesRepository extends JpaRepository<RecetasIngredientes, Integer> {
    @Query(value = """
        SELECT i.nombre_ingrediente, COUNT(*) as total
        FROM recetas_ingredientes ri
        JOIN ingredientes i ON ri.id_ingrediente = i.id_ingrediente
        GROUP BY i.id_ingrediente, i.nombre_ingrediente
        ORDER BY total DESC, i.nombre_ingrediente ASC
        LIMIT 5""", nativeQuery = true)
    List<Object[]> findTop5IngredientesMasUtilizados();

    @Query(value = "SELECT i.nombre_ingrediente, ri.cantidad " +
            "FROM recetas_ingredientes ri " +
            "JOIN ingredientes i ON ri.id_ingrediente = i.id_ingrediente " +
            "WHERE ri.id_receta = :idReceta",
            nativeQuery = true)
    List<Object[]> obtenerIngredientesDeReceta(@Param("id_receta") Integer idReceta);
}

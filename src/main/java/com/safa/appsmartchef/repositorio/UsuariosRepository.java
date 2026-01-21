package com.safa.appsmartchef.repositorio;

import com.safa.appsmartchef.dto.FavoritoMasPopularDTO;
import com.safa.appsmartchef.modelos.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Boolean existsByNombreUsuarioEqualsIgnoreCase(String nombre);

    @Query(value = """
    SELECT 
        u.id_usuario AS usuarioId,
        u.nombre_usuario AS nombreUsuario,
        u.email AS email,
        r.id_receta AS recetaId,
        r.nombre AS nombreReceta,
        r.imagen AS imagenReceta,
        r.descripcion AS descripcionReceta,
        t.nombre_tipo AS tipoReceta,
        fav.total_favoritos AS vecesGuardadaComoFavorita
    FROM usuarios u
    INNER JOIN usuarios_recetas ur 
        ON u.id_usuario = ur.id_usuario
    INNER JOIN recetas r 
        ON ur.id_recetas = r.id_receta
    INNER JOIN tipos t 
        ON r.id_tipo = t.id_tipo
    INNER JOIN (
        SELECT 
            ur2.id_recetas AS id_receta,
            COUNT(*) AS total_favoritos
        FROM usuarios_recetas ur2
        GROUP BY ur2.id_recetas
        HAVING COUNT(*) = (
            SELECT MAX(contador)
            FROM (
                SELECT COUNT(*) AS contador
                FROM usuarios_recetas
                GROUP BY id_recetas
            ) sub
        )
    ) fav 
        ON r.id_receta = fav.id_receta
    ORDER BY fav.total_favoritos DESC, u.nombre_usuario
""", nativeQuery = true)
    List<FavoritoMasPopularDTO> findUsuariosConRecetaMasFavorita();



    boolean existsByEmailEqualsIgnoreCase(String email);

}

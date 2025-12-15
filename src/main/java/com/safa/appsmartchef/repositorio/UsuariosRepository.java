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
        tipo_receta.nombre_tipo AS tipoReceta,
        contador.total_favoritos AS vecesGuardadaComoFavorita
    FROM usuarios u
    INNER JOIN usuarios_recetas ur ON u.id_usuario = ur.id_usuario
    INNER JOIN recetas r ON ur.id_receta = r.id_receta
    INNER JOIN tipos tipo_receta ON r.id_tipo = tipo_receta.id_tipo
    INNER JOIN (
        SELECT id_receta, COUNT(*) AS total_favoritos
        FROM usuarios_recetas
        GROUP BY id_receta
        HAVING COUNT(*) = (
            SELECT COUNT(*)
            FROM usuarios_recetas
            GROUP BY id_receta
            ORDER BY COUNT(*) DESC
            LIMIT 1
        )
    ) contador ON r.id_receta = contador.id_receta
    ORDER BY contador.total_favoritos DESC, u.nombre_usuario
    """, nativeQuery = true)
    List<FavoritoMasPopularDTO> findUsuariosConRecetaMasFavorita();

    boolean existsByEmailEqualsIgnoreCase(String email);

}

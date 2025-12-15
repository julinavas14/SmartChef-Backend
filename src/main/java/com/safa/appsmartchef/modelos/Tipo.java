package com.safa.appsmartchef.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipos")
public class Tipo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer id;

    @Column(name = "nombre_tipo")
    private String nombre;

    @OneToMany(mappedBy = "tipo")
    @JsonIgnore
    private List<Usuarios> usuarios = new ArrayList<>();
}

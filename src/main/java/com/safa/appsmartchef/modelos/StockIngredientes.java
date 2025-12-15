package com.safa.appsmartchef.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock_ingredientes")
public class StockIngredientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_stock_ingredientes;

    @ManyToOne
    @JoinColumn(name = "id_stock")
    private Stock id_stock;

    @ManyToOne
    @JoinColumn(name = "id_ingrediente")
    private Ingredientes id_ingrediente;

    private float cantidad;

    private String unidad;
}

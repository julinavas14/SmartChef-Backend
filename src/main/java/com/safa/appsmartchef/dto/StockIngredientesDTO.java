package com.safa.appsmartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockIngredientesDTO {
    private Integer id_stock_ingredientes;
    private StockDTO id_stock;
    private IngredientesDTO id_ingrediente;
    private float cantidad;
    private String unidad;
}

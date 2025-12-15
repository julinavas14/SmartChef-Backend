package com.safa.appsmartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockDTO {
    private Integer id_stock;
    private UsuariosDTO id_usuario;
}

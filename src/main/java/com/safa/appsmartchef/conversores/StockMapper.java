package com.safa.appsmartchef.conversores;

import com.safa.appsmartchef.dto.StockDTO;
import com.safa.appsmartchef.modelos.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {
    Stock convertirAEntity(StockDTO dto);
    StockDTO convertirADTO(Stock entity);
}

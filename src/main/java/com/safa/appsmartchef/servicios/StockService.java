package com.safa.appsmartchef.servicios;

import com.safa.appsmartchef.conversores.StockMapper;
import com.safa.appsmartchef.dto.StockDTO;
import com.safa.appsmartchef.repositorio.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {
    StockRepository stockRepository;
    StockMapper stockMapper;

    public List<StockDTO> buscarTodos(){
        return stockRepository.findAll().stream().map(stockMapper::convertirADTO).collect(Collectors.toList());
    }
}

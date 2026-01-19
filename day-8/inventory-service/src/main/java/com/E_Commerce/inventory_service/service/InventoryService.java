package com.E_Commerce.inventory_service.service;


import com.E_Commerce.inventory_service.dto.InventoryResponse;
import com.E_Commerce.inventory_service.model.Inventory;
import com.E_Commerce.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> inStock(List<String> code) {
        log.info("wait started");
        Thread.sleep(10000);
        log.info("wait ended");
        return inventoryRepository.findByCodeIn(code).stream()
                .map(inventory ->
                    InventoryResponse.builder().code(inventory.getCode())
                            .inStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }
}

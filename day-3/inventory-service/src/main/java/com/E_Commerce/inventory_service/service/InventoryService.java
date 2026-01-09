package com.E_Commerce.inventory_service.service;


import com.E_Commerce.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String code) {
        return inventoryRepository.findByCode(code)
                .map(inventory -> inventory.getQuantity() > 0)
                .orElse(false);
    }
}

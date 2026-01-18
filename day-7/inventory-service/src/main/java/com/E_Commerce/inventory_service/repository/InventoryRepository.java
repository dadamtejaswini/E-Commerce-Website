package com.E_Commerce.inventory_service.repository;

import com.E_Commerce.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findByCode(String code);
    List<Inventory> findByCodeIn(List<String> code);
}

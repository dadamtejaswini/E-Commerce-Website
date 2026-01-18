package com.E_Commerce.inventory_service;

import com.E_Commerce.inventory_service.model.Inventory;
import com.E_Commerce.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			inventoryRepository.deleteAll();

			Inventory inventory = new Inventory();
			inventory.setCode("iphone_17_pro_max");
			inventory.setQuantity(25);

			Inventory inventory1 = new Inventory();
			inventory1.setCode("iphone_17_pro");
			inventory1.setQuantity(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}

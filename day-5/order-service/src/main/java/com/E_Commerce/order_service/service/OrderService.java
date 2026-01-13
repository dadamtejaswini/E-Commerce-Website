package com.E_Commerce.order_service.service;

import com.E_Commerce.order_service.dto.InventoryResponse;
import com.E_Commerce.order_service.dto.ItemsDto;
import com.E_Commerce.order_service.dto.OrderRequest;
import com.E_Commerce.order_service.model.Items;
import com.E_Commerce.order_service.model.Orders;
import com.E_Commerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest){
        Orders orders=new Orders();
        orders.setOrderNum(UUID.randomUUID().toString());

        List<Items> orderItems= orderRequest.getItemsDtoList().stream().map(this::mapToDto).toList();

        orders.setItemsList(orderItems);

        List<String> codes= orders.getItemsList().stream().map(Items::getItemCode).toList();

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("code", codes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductsInStock =
                Arrays.stream(inventoryResponses)
                        .allMatch(response -> Boolean.TRUE.equals(response.getInStock()));


        if (allProductsInStock){
            orderRepository.save(orders);
        }else{
            throw new IllegalArgumentException("product out off stock");
        }


    }



    private Items mapToDto(ItemsDto itemsDto) {
        Items items = new Items();
        items.setPrice(itemsDto.getPrice());
        items.setQuantity(itemsDto.getQuantity());
        items.setItemCode(itemsDto.getItemCode());
        return items;
    }
}

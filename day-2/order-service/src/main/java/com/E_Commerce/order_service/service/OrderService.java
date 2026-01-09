package com.E_Commerce.order_service.service;

import com.E_Commerce.order_service.dto.ItemsDto;
import com.E_Commerce.order_service.dto.OrderRequest;
import com.E_Commerce.order_service.model.Items;
import com.E_Commerce.order_service.model.Orders;
import com.E_Commerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Orders orders=new Orders();
        orders.setOrderNum(UUID.randomUUID().toString());

        List<Items> orderItems= orderRequest.getItemsDtoList().stream().map(this::mapToDto).toList();

        orders.setItemsList(orderItems);

        orderRepository.save(orders);

    }

    private Items mapToDto(ItemsDto itemsDto) {
        Items items = new Items();
        items.setPrice(itemsDto.getPrice());
        items.setQuantity(itemsDto.getQuantity());
        items.setItemCode(itemsDto.getItemCode());
        return items;
    }
}

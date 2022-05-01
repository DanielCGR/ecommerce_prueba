package com.upc.ecommerce.util;

import com.upc.ecommerce.dto.OrderRequest;
import com.upc.ecommerce.dto.OrderResponse;
import com.upc.ecommerce.entities.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {

    //Inyección de dependencias (DI)
    private ModelMapper modelMaper;

    public EntityDtoConverter(ModelMapper modelMaper){
        this.modelMaper = modelMaper;
    }

    public OrderResponse convertEntityToDto(Order order){
        return modelMaper.map(order, OrderResponse.class);
    }

    public List<OrderResponse> convertEntityToDto(List<Order> orders){
        return orders.stream()
                .map(order->convertEntityToDto(order))
                .collect(Collectors.toList());
    }

}

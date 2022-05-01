package com.upc.ecommerce.controllers;

import com.upc.ecommerce.dto.OrderRequest;
import com.upc.ecommerce.dto.OrderResponse;
import com.upc.ecommerce.entities.Order;
import com.upc.ecommerce.service.OrderService;
import com.upc.ecommerce.util.EntityDtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;
    private EntityDtoConverter converter;

    public OrderController(OrderService orderService, EntityDtoConverter converter){
        this.orderService = orderService;
        this.converter = converter;
    }

    @GetMapping("/account/{accountID}")
    public ResponseEntity<List<OrderResponse>> findAllOrders(@PathVariable String accountID){

        List<Order> orders = orderService.getAllOrderByAccountID(accountID);

        return new ResponseEntity<>(converter.convertEntityToDto(orders), HttpStatus.OK);

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable String orderId){

        Order order = orderService.getOrderById(orderId);

        return new ResponseEntity<>(converter.convertEntityToDto(order), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request){

        Order order = orderService.createOrder(request);

        return new ResponseEntity<>(converter.convertEntityToDto(order), HttpStatus.CREATED);

    }

}

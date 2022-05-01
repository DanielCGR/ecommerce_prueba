package com.upc.ecommerce.service;

import com.upc.ecommerce.dto.OrderRequest;
import com.upc.ecommerce.entities.Order;
import com.upc.ecommerce.entities.OrderDetail;
import com.upc.ecommerce.repository.OrderRepository;
import com.upc.ecommerce.util.OrderStatus;
import com.upc.ecommerce.util.OrderValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {


    private OrderRepository orderRepository;

    public OrderService(OrderRepository repository){
        this.orderRepository = repository;
    }

    @Transactional(readOnly = true)
    public Order getOrderById(String orderId){
        return orderRepository.findOrderByOrderId(orderId);
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrderByAccountID(String accountID){
        return orderRepository.findOrdersByAccountID(accountID);
    }
    @Transactional
    public Order createOrder (OrderRequest orderRequest){

        OrderValidator.validateOrder(orderRequest);
        Order orderNew = initOrder(orderRequest);
        return orderRepository.save(orderNew);

    }

    private Order initOrder(OrderRequest orderRequest){
        Order orderObj = new Order();

        orderObj.setOrderId(UUID.randomUUID().toString());
        orderObj.setAccountID(orderRequest.getAccountID());
        orderObj.setStatus(OrderStatus.PENDING);

        List<OrderDetail> orderDetail = orderRequest.getItems()
                .stream()
                .map(item->OrderDetail.builder()
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .upc(item.getUpc())
                        .tax(item.getPrice() * item.getQuantity() * 0.16)
                        .totalAmount(item.getPrice() * item.getQuantity())
                        .order(orderObj).build()
                ).collect(Collectors.toList());

        orderObj.setDetails(orderDetail);
        orderObj.setTotalAmount(orderDetail.
                    stream().mapToDouble(OrderDetail::getTotalAmount).sum());
        orderObj.setTotalTax(orderObj.getTotalAmount()*0.16);
        orderObj.setTotalAmountTax(orderObj.getTotalAmount() + orderObj.getTotalTax());
        orderObj.setTransactionDate(new Date()); //1.29.05

        return orderObj;
    }

}

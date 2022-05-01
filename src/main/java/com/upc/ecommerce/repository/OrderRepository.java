package com.upc.ecommerce.repository;

import com.upc.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o Where o.accountID=?1") //JPQL
    List<Order> findOrdersByAccountID(String accountID);

    Order findOrderByOrderId(String orderId);

    @Query(value = "SELECT * FROM orders o WHERE o.order_id=?1", nativeQuery = true)
    Order findOrderByOrderIdNative(String orderId);

}

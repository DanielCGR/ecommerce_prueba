package com.upc.ecommerce.entities;

import com.upc.ecommerce.util.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "order_id", nullable = false,length = 50)
    private String orderId;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "account_id")
    private String accountID;

    @Column(name = "total_tax")
    private Double totalTax;

    @Column(name = "total_amount_tax")
    private Double totalAmountTax;

    @Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate ;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> details;

}

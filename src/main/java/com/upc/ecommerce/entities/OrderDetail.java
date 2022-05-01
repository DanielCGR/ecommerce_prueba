package com.upc.ecommerce.entities;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_details")
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "upc")
    private String upc;

    @Column(name = "total_amount")
    private Double totalAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;

}

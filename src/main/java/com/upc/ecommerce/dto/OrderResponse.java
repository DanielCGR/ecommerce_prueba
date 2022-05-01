package com.upc.ecommerce.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {
    private String orderID;
    private String status;
    private String accountID;
    private Double totalAmount;
    private Double totalTax;
    private Double totalAmountTax;
    private Date transactionDate;

    List<OrderDetailResponse> details;

}

package com.upc.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {

    @NotBlank
    @NotNull
    private String accountID;

    @NotNull
    private List<LineItem> items;

}

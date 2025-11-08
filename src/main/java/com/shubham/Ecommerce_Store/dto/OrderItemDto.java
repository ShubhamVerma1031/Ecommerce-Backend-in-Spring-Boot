package com.shubham.Ecommerce_Store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data

public class OrderItemDto {


private String productName;
private int quantity;
private double productPrice;

    public OrderItemDto( String productName,  double productPrice,int quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
}

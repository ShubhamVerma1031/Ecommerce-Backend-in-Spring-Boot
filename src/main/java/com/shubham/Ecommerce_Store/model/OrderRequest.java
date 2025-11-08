package com.shubham.Ecommerce_Store.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Map<Long,Integer> getProductQuantities;
    private double totalAmount;
}

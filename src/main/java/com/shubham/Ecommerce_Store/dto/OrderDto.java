package com.shubham.Ecommerce_Store.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class OrderDto {

private Long id;
private String userName;
private String email;
private double totalAmount;
private Date orderDate;
private String status;
private List<OrderItemDto> orderItems;

    public OrderDto(Long id, String userName, String email, double totalAmount, Date orderdate, String status, List<OrderItemDto> orderItems) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.totalAmount = totalAmount;
        this.orderDate = orderdate;
        this.status = status;
        this.orderItems = orderItems;
    }

    public OrderDto(Long id, double totalAmount, Date orderdate, String status, List<OrderItemDto> orderItems) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.orderDate = orderdate;
        this.status = status;
        this.orderItems = orderItems;
    }
}

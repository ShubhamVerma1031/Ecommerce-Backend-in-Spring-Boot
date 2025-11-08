package com.shubham.Ecommerce_Store.controller;

import com.shubham.Ecommerce_Store.dto.OrderDto;
import com.shubham.Ecommerce_Store.model.OrderRequest;
import com.shubham.Ecommerce_Store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    public OrderDto placeOrder(@PathVariable Long userId, @RequestBody OrderRequest orderRequest){
        return orderService.placeOrder(userId,orderRequest.getGetProductQuantities(),orderRequest.getTotalAmount());
    }
    @GetMapping
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{userId}")
    public List<OrderDto> getOrderByUser(@PathVariable Long userId){

        return orderService.getOrderByUser(userId);
    }

}

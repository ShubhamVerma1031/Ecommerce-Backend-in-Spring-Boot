package com.shubham.Ecommerce_Store.service;

import com.shubham.Ecommerce_Store.dto.OrderDto;
import com.shubham.Ecommerce_Store.dto.OrderItemDto;
import com.shubham.Ecommerce_Store.model.OrderItem;
import com.shubham.Ecommerce_Store.model.Orders;
import com.shubham.Ecommerce_Store.model.Product;
import com.shubham.Ecommerce_Store.model.User;
import com.shubham.Ecommerce_Store.repository.OrderRepository;
import com.shubham.Ecommerce_Store.repository.ProductRepository;
import com.shubham.Ecommerce_Store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequestMapping("api/order")
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
// place Order
//1. User sends product IDs + quantities
//2. Service fetches user
//3. Create an empty Order
// . orderItem object
   // . orderItemDto object
//4. For each product:
//            - Fetch product
//      - Create OrderItem
//      - Create OrderItemDTO
//5. Save full Order
//6. Return OrderDTO to frontend

    public OrderDto placeOrder(Long userId, Map<Long,Integer> productQuantities,double totalAmount){
     User user = userRepository.findById(userId)
        .orElseThrow(()->new RuntimeException("User not found."));
        Orders order = new Orders();
        order.setUser(user);
        order.setStatus("pending");
        order.setTotalAmount(totalAmount);

        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemDto> orderItemDtos = new ArrayList<>();

        for (Map.Entry<Long,Integer> entry:productQuantities.entrySet()){
            Product product = productRepository.findById(entry.getKey())
                    .orElseThrow(()->new RuntimeException("Product not found"));

            OrderItem orderItem =new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(entry.getValue());
            orderItems.add(orderItem);

            orderItemDtos.add(new OrderItemDto(product.getName(),product.getPrice(),entry.getValue()));

        }
        order.setOrderItems(orderItems);
        Orders saveOrder = orderRepository.save(order);

        return new OrderDto(saveOrder.getId(),saveOrder.getTotalAmount(),saveOrder.getOrderDate(),saveOrder.getStatus(),orderItemDtos);

    }
    public List<OrderDto> getAllOrders(){
        List<Orders> orders = orderRepository.findAllOrdersWithUsers();
        return orders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private OrderDto convertToDto(Orders orders) {

        List<OrderItemDto> orderItems = orders.getOrderItems().stream()
                .map(orderItem -> new OrderItemDto(
                        orderItem.getProduct().getName(),
                        orderItem.getProduct().getPrice(),
                        orderItem.getQuantity()
                )).collect(Collectors.toList());
        return new OrderDto(
                orders.getId(),
                orders.getUser() != null ? orders.getUser().getName() : "Unknown",
                orders.getUser() != null ? orders.getUser().getEmail() : "Unknown",
                orders.getTotalAmount(),
                orders.getOrderDate(),
                orders.getStatus(),
                orderItems
        );



    }
    public List<OrderDto> getOrderByUser(Long userId) {
        Optional<User> userOp = userRepository.findById(userId);
        if(userOp.isEmpty())
        {
            throw  new RuntimeException("user not found");
        }
        User user= userOp.get();
        List<Orders> ordersList = orderRepository.findByUser(user);
        return ordersList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}

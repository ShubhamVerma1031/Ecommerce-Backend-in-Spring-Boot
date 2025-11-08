package com.shubham.Ecommerce_Store.repository;

import com.shubham.Ecommerce_Store.model.Orders;
import com.shubham.Ecommerce_Store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    //Fetch Orders + Users together
    @Query("SELECT o from Orders o JOIN FETCH o.user")

    List<Orders> findAllOrdersWithUsers();

    List<Orders> findByUser(User user);
}

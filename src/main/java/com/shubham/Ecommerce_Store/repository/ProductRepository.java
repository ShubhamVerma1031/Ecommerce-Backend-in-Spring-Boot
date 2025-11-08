package com.shubham.Ecommerce_Store.repository;

import com.shubham.Ecommerce_Store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}

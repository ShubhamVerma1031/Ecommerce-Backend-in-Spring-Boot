package com.shubham.Ecommerce_Store.service;

import com.shubham.Ecommerce_Store.model.Product;
import com.shubham.Ecommerce_Store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("product not found with id : "+id));
    }
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProductByid(Long id , Product product) {
       Product newProduct = new Product();
       newProduct.setId(product.getId());
       newProduct.setName(product.getName());
       newProduct.setCategory(product.getCategory());
       newProduct.setPrice(product.getPrice());
       newProduct.setDescription(product.getDescription());
       newProduct.setImageUrl(product.getImageUrl());
       return productRepository.save(newProduct);
    }
}

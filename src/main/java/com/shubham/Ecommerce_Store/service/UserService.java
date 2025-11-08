package com.shubham.Ecommerce_Store.service;

import com.shubham.Ecommerce_Store.model.User;
import com.shubham.Ecommerce_Store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found with id : "+id));
    }

    public User registerUser(User user){
        try {
            User newUser = userRepository.save(user);
            System.out.println("registration Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User loginUser(String email,String password){

        User user = userRepository.findByEmail(email);
        if (user!=null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}

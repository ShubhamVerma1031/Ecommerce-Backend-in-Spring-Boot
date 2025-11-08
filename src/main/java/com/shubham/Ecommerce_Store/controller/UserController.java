package com.shubham.Ecommerce_Store.controller;

import com.shubham.Ecommerce_Store.model.User;
import com.shubham.Ecommerce_Store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("api/user")
public class UserController
{
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }
    public User loginUser(@RequestBody User user){
        return userService.loginUser(user.getEmail(), user.getPassword());
    }
    @GetMapping("/{id}")
    public User getuserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

}

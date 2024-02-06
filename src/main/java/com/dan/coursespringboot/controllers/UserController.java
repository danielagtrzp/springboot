package com.dan.coursespringboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dan.coursespringboot.entities.User;
import com.dan.coursespringboot.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {

    //Autowired userService
    @Autowired
    private UserService userService;

    //getAllUsersMethod

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //createUserMethod
    //@RequestBody: to show the type that needs to match in the request
    //@PostMapping: create
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    //updateUserById
    @PutMapping("users/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
        return userService.updateuserById(id, user);
    }

    //deleteUserById
    @DeleteMapping("users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    //getUserByUsename
    @GetMapping("users/byusername/{username}")
    public User getMethodName(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
    


}

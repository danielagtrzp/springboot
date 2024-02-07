package com.dan.coursespringboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.dan.coursespringboot.entities.User;
import com.dan.coursespringboot.exceptions.UserExistException;
import com.dan.coursespringboot.exceptions.UserNotFoundException;
import com.dan.coursespringboot.exceptions.UsernameNotFoundException;
import com.dan.coursespringboot.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@Validated
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
    //@Valid shows why the validation does not passed and returned a template format response
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder){
        try {
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders(); 
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (UserExistException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //updateUserById
    @PutMapping("users/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
        try {
            return userService.updateuserById(id, user);    
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //deleteUserById
    @DeleteMapping("users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    //getUserByUsename
    @GetMapping("users/byusername/{username}")
    public User getMethodName(@PathVariable String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user==null) {
            throw new UsernameNotFoundException("Username:" + username + " not found in the repository");
        }
        return user;
    }
    


}

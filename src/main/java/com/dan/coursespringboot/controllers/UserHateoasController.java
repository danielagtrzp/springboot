package com.dan.coursespringboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dan.coursespringboot.entities.Order;
import com.dan.coursespringboot.entities.User;
import com.dan.coursespringboot.exceptions.UserNotFoundException;
import com.dan.coursespringboot.repositories.UserRepository;
import com.dan.coursespringboot.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @SuppressWarnings("null")
    @GetMapping
    public CollectionModel<User> getAllUsers() throws UserNotFoundException{
        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            //selflink
            Long userid = user.getUserid();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
            user.add(selfLink);

            //relationship links for orders
            CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
            Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
            user.add(ordersLink);
        }
        Link selfLinkForGetAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();

        CollectionModel<User> finalEntities = CollectionModel.of(allUsers,selfLinkForGetAllUsers); 
        return finalEntities;
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id){
        try {

            Optional<User> ouser = userService.getUserById(id);
            User user = ouser.get(); 
            Long userid = user.getUserid();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
            user.add(selfLink);
            EntityModel<User> finalEntity = EntityModel.of(user);
            return finalEntity; 
        } catch (UserNotFoundException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

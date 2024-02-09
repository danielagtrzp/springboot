package com.dan.coursespringboot.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.coursespringboot.entities.Order;
import com.dan.coursespringboot.entities.User;
import com.dan.coursespringboot.exceptions.UserNotFoundException;
import com.dan.coursespringboot.repositories.OrderRepository;
import com.dan.coursespringboot.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class OrderHateoasController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @SuppressWarnings("null")
    @GetMapping("/{userid}/orders")
    public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
        Optional<User> optionalUser = userRepository.findById(userid);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User Not Found");
        }
        List<Order> allOrders = optionalUser.get().getOrders();
        CollectionModel<Order> finalEntities = CollectionModel.of(allOrders);
        return finalEntities;
    }
}

package com.dan.coursespringboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.coursespringboot.entities.Order;
import com.dan.coursespringboot.entities.User;
import com.dan.coursespringboot.exceptions.OrderNotFoundException;
import com.dan.coursespringboot.exceptions.UserNotFoundException;
import com.dan.coursespringboot.repositories.OrderRepository;
import com.dan.coursespringboot.repositories.UserRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Order managment RESTFull services", description = "Controller dor Order Managment")
public class OrderController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public OrderRepository orderRepository;

    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
        Optional<User> optionalUser = userRepository.findById(userid);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User Not Found");
        }
        return optionalUser.get().getOrders();
    }

    @PostMapping("/{userid}/orders")
    public void createOrder(@PathVariable Long userid,@RequestBody Order order) throws UserNotFoundException{
        Optional<User> optionalUser = userRepository.findById(userid);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User Not Found");
        }

        User user = optionalUser.get();
        order.setUser(user);
        orderRepository.save(order);

    }

    @GetMapping("/{userid}/orders/{orderid}")
    public Order getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException, OrderNotFoundException{

        Optional<User> optionalUser = userRepository.findById(userid);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User Not Found");
        }

        Optional<Order> optionalOrder = orderRepository.findById(orderid);
        if (!optionalOrder.isPresent()) {
            throw new OrderNotFoundException("Order Not Found");
        }

        Order order = optionalOrder.get();
        return order;

    }

}

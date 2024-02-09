package com.dan.coursespringboot.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dan.coursespringboot.entities.User;
import com.dan.coursespringboot.exceptions.UserNotFoundException;
import com.dan.coursespringboot.services.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.constraints.Min;

//this controller is just to explain Mapping jackson dynamic filtering
@RestController
@RequestMapping(value = "/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {
    //Autowired userService
    @Autowired
    private UserService userService;
    //first with hashset
    @GetMapping("/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            Optional<User> userById = userService.getUserById(id);
            User user = userById.get();
            MappingJacksonValue mapper = new MappingJacksonValue(user);

            Set<String> fields = new HashSet<>();
            fields.add("userid");
            fields.add("username");
            fields.add("ssn");

            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            mapper.setFilters(filterProvider);

            return mapper;
        } catch (UserNotFoundException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //with params completed dynamic with @RequestParam
    @GetMapping("/params/{id}")
    public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id , @RequestParam Set<String> fields){
        try {
            Optional<User> userById = userService.getUserById(id);
            User user = userById.get();
            MappingJacksonValue mapper = new MappingJacksonValue(user);

            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            mapper.setFilters(filterProvider);

            return mapper;
        } catch (UserNotFoundException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

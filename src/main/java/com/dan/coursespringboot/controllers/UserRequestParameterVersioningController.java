package com.dan.coursespringboot.controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.coursespringboot.dtos.UserDtoV1;
import com.dan.coursespringboot.dtos.UserDtoV2;
import com.dan.coursespringboot.dtos.UserMmDto;
import com.dan.coursespringboot.entities.User;
import com.dan.coursespringboot.exceptions.UserNotFoundException;
import com.dan.coursespringboot.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/versioning/params/users")
public class UserRequestParameterVersioningController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    //VERSIONING REQUEST PARAMETER V1
    @GetMapping(value =  "/{id}", params="version=1")
    public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{
        
        Optional<User> userOptional = userService.getUserById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("user not found");
        }
        User user = userOptional.get();
        UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
        return userDtoV1;
    
    }

    //VERSIONING REQUEST PARAMETER V2
    @GetMapping(value =  "/{id}", params="version=2")
    public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{
        
        Optional<User> userOptional = userService.getUserById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("user not found");
        }
        User user = userOptional.get();
        UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
        return userDtoV2;
    
    }

}

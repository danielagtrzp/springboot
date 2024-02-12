package com.dan.coursespringboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.coursespringboot.dtos.UserMsDto;
import com.dan.coursespringboot.entities.User;
import com.dan.coursespringboot.mappers.UserMapper;
import com.dan.coursespringboot.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserMsDto> getAllUsersDtos(){
        return userMapper.usersToUsersMsDto(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public UserMsDto getUserByID(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();

        return userMapper.userToUserMsDto(user);
    }
    

}

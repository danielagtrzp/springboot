package com.dan.coursespringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.coursespringboot.entities.User;
import com.dan.coursespringboot.repositories.UserRepository;
//Service
@Service
public class UserService {
    //Autowired the userRepository
    @Autowired
    private UserRepository userRepository;

    //getAllUsers Method
    public List<User> getAllUsers(){
       return userRepository.findAll();
    }

    //createUser method
    public User createUser(User user){
        return userRepository.save(user); 
    }

    //getUserById
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    //updateUserById
    public User updateuserById(Long id, User user){
        user.setId(id);
        return userRepository.save(user);
    }

    //deleteUserById
    public void deleteUserById(Long id){
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    //getUserByUserName
    public User getUserByUsername(String username){
       return userRepository.findByUsername(username);
    }
}
package com.dan.coursespringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dan.coursespringboot.entities.User;
import com.dan.coursespringboot.exceptions.UserExistException;
import com.dan.coursespringboot.exceptions.UserNotFoundException;
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
    public User createUser(User user) throws UserExistException{
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UserExistException("User Already exist in repository");
        }
        return userRepository.save(user); 
    }

    //getUserById
    public Optional<User> getUserById(Long id) throws UserNotFoundException{
        
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User Not found in user Repository");
        }
        return user;
    }

    //updateUserById
    public User updateuserById(Long id, User user) throws UserNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User Not found in user Repository, provide the corrct userId");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    //deleteUserById
    public void deleteUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not found in user Repository, provide the corrct userId");
        }
        userRepository.deleteById(id); 
    }

    //getUserByUserName
    public User getUserByUsername(String username){
       return userRepository.findByUsername(username);
    }
}
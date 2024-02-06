package com.dan.coursespringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dan.coursespringboot.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    //custom method that seeks by username
    //returns just one user and not a list because the unique anotation in entity
    User findByUsername(String username);
}

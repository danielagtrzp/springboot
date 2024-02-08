package com.dan.coursespringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dan.coursespringboot.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}

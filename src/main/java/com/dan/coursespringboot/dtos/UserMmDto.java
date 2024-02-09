package com.dan.coursespringboot.dtos;

import java.util.List;

import com.dan.coursespringboot.entities.Order;

//dtos are always recommended because if there is other field we need to add to the response just adding it here it can be achieved
//without modify the entity because the entity as we saw in the course is used for many services
public class UserMmDto {

    private Long userid;
    private String username;
    private String firstname;
    private List<Order> orders;

    
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    
}

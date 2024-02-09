package com.dan.coursespringboot.entities;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


//Entity: pojos representing data that can be persisted to the db
// -> any instance of an entity represents row in a table 
// -> entity name default is the name of the class
// @Entity(name = "Users")
//@Entity(name = "users")
//@Table is an optional annotation that you can override default table name, schema name etc.
@Entity
@Table(name = "users") 
//@JsonIgnoreProperties({"firstname","lastname"})
//otrhe aproach to ignore properties -staticfiltering JsonIgnore
@JsonFilter(value = "userFilter")//to use the mapping jackson in thecontroller
public class User extends RepresentationModel<User>{
    //with this JPA makes this our primary key
    @Id
    //JPA autogenerates the value
    @GeneratedValue 
    private Long userid;

    @NotEmpty(message = "Username is Mandatory field. Please provide username")
    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    private String username;

    @Size(min=2, message="FirstName should have atleast 2 characters")
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstname;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastname;

    @Column(name = "EMAIL_ADRESS", length = 50, nullable = false)
    private String email;

    @Column(name = "ROLE", length = 50, nullable = false)
    private String role;

    @Column(name = "SSN", length = 50, nullable = false, unique = true)
    //@JsonIgnore
    //hides ssn from the response, the problem is that if I create a new user or update it will be an error because of nulleable arg 
    //also I can set nullable as true and it will work, just check if put and post still working -staticfiltering JsonIgnore
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    //NO ARGUMENT CONSTRUCTOR 
    public User() {}

    //FIELDS CONSTRUCTOR
    public User(Long userid, String username, String firstname, String lastname, String email, String role, String ssn) {
        this.userid = userid;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    //GETTERS & SETTERS

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User [userid=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname="
                + lastname + ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + "]";
    }
    
}

package com.dan.coursespringboot.Hello;

public class UserDetails {
    public String firstname;
    public String lastname;
    public String city;
 
    //Fields constructor
    public UserDetails(String firstname, String lastname, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
    }

    //Getters & setters
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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    //to string 
    @Override
    public String toString() {
        return "UserDetails [firstname=" + firstname + ", lastname=" + lastname + ", city=" + city + "]";
    }

    
    
}

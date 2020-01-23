package com.example.myapplication.model;

import java.util.List;

public class UserList {

    private List<User> country = null;

    public List<User> getUserList() {
        return country;
    }

    public void setCountry(List<User> country) {
        this.country = country;
    }
}

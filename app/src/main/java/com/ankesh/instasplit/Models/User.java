package com.ankesh.instasplit.Models;

/**
 * Created by I324832 on 5/6/2017.
 */

public class User {

    public String mobile_number;

    public String first_name;
    public String last_name;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String firstname, String lastname, String mobilenumber) {
        this.mobile_number = mobilenumber;
        this.first_name = firstname;
        this.last_name = lastname;
    }
}

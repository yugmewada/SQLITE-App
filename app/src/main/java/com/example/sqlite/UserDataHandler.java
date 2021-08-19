package com.example.sqlite;

public class UserDataHandler {

    String id;
    String fname;
    String lname;
    String email;
    String password;
    String contact;

    public UserDataHandler(String id, String fname, String lname, String email, String password, String contact) {
        this.id= id;
        this.fname= fname;
        this.lname= lname;
        this.email= email;
        this.password= password;
        this.contact= contact;
    }

    public String getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }
}

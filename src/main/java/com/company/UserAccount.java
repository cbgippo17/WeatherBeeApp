package com.company;

public class UserAccount {
    private int id;
    private String username;
    private String password;
    private String email;
    private int numOfHives;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumOfHives() {
        return numOfHives;
    }

    public void setNumOfHives(int numOfHives) {
        this.numOfHives = numOfHives;
    }
}

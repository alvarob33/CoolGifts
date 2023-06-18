package com.example.coolgifts.users;
public class User {
    int id;
    private String name;
    private String email;
    private String image;

    public User(int id, String name, String email, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
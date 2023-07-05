package com.example.coolgifts.users;

public class FriendRequest {
    private int id;
    private String username;
    private String email;
    private String image;

    public FriendRequest(int id, String username, String email, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }
}

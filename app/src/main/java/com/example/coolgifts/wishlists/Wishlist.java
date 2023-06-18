package com.example.coolgifts.wishlists;

import com.example.coolgifts.presents.Present;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    private int id;
    private String name;
    private String description;
    private int userId;
    private ArrayList<Present> presents;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;

    public Wishlist(int id, String name, String description, int userId, ArrayList<Present> gifts,
                    LocalDateTime creationDate, LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.presents = gifts;
        this.creationDate = creationDate;
        this.endDate = endDate;
    }

    public Wishlist(String name, LocalDateTime endDate) {
        this.name = name;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public ArrayList<Present> getPresents() {
        return presents;
    }
}

package com.example.coolgifts.presents;

import android.app.Activity;

import com.example.coolgifts.api.APIPresent;

public class Present {
    private int id;
    private int wishlistId;
    private int priority;
    private boolean booked;

    //Product atributes
    private String name;
    private String description;
    private String link;
    private String photo;
    private double price;
    private int categoryId;


    public Present(int id, int wishlistId, String productUrl, int priority, boolean booked, Activity activity) {
        this.id = id;
        this.wishlistId = wishlistId;
        this.priority = priority;
        this.booked = booked;
        APIPresent.setProductAtributes(this, productUrl, activity);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}

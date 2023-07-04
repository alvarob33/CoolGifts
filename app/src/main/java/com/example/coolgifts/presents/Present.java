package com.example.coolgifts.presents;

import android.app.Activity;

import com.example.coolgifts.api.APIPresent;

public class Present {
    private int id;
    private int wishlistId;
    private String productUrl;
    private int priority;
    private int booked;

    //Product atributes
    private String name;
    private String description;
    private String link;
    private String photo;
    private double price;
    private int[] categoryIds;


    public Present(int id, int wishlistId, String productUrl, int priority, int booked, Activity activity) {
        this.id = id;
        this.wishlistId = wishlistId;
        this.productUrl = productUrl;
        this.priority = priority;
        this.booked = booked;
        APIPresent.setProductAtributes(this, productUrl, activity);

    }

    //Para products
    public Present(int id, String name, String description, String link, String photo, double price, int[] categoryIds, Activity activity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productUrl = "https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/products/" + id;
        this.link = link;
        this.photo = photo;
        this.price = price;
        this.categoryIds = categoryIds;

        this.priority = 33;
        this.booked = 0;
        //APIPresent.setProductAtributes(this, productUrl, activity);
    }


    public int getId() {
        return id;
    }

    public String getProductUrl() {
        return productUrl;
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

    public void setCategoryIds(int[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public boolean isBooked() {
        return booked == 1;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }
}

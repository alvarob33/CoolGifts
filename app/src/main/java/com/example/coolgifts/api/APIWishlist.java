package com.example.coolgifts.api;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.coolgifts.RegisterActivity;
import com.example.coolgifts.presents.Present;
import com.example.coolgifts.wishlists.WishAdapter;
import com.example.coolgifts.wishlists.Wishlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class APIWishlist {

    public static void createWishlist(Wishlist newWishlist, Activity activity) {

        //Obtenemos token del usuario registrado
        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        JSONObject wishlist = new JSONObject();
        try{
            wishlist.put("name", newWishlist.getName());
            wishlist.put("description", "");
            wishlist.put("end_date", newWishlist.getEndDate());
        }catch (Exception e){
            Log.e("Error","Error añadiendo al Json");
        }
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST,"https://balandrau.salle.url.edu/i3/socialgift/api/v1/wishlists",wishlist,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", response.toString());

                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error: ", error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer " + loginToken.getToken());
                return params;
            }
        };
        queue.add(jor);
    }

    public static void getWishlistsFromCurrentUser(ArrayList<Wishlist> wishlists, Activity activity, WishAdapter wishAdapter) {

        //Obtenemos token del usuario registrado
        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        //Peticion
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/" + loginToken.getId() + "/wishlists", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response: ", response.toString());
                        try {
                            parseWishlistsFromJSON(response, activity, wishAdapter);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error: ", error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer " + loginToken.getToken());
                return params;
            }
        };
        queue.add(jsonArrayRequest);
    }

    public static void getWishlistsFromUser(int userId, ArrayList<Wishlist> wishlists, Activity activity, WishAdapter wishAdapter) {

        //Obtenemos token del usuario registrado
        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        //Peticion
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET,"https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/" +userId+ "/wishlists", null,
                new Response.Listener<JSONArray >() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response: ", response.toString());
                        try {
                            parseWishlistsFromJSON(response, activity, wishAdapter);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error: ", error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer " + loginToken.getToken());
                return params;
            }
        };
        queue.add(jar);
    }


    private static void parseWishlistsFromJSON(JSONArray wishlistArray, Activity activity, WishAdapter wishAdapter) throws JSONException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        for (int i = 0; i < wishlistArray.length(); i++) {
            JSONObject wishlistObject = wishlistArray.getJSONObject(i);

            int id = wishlistObject.getInt("id");
            String name = wishlistObject.getString("name");
            String description = wishlistObject.getString("description");
            int userId = wishlistObject.getInt("user_id");

            JSONArray giftsArray = wishlistObject.getJSONArray("gifts");
            ArrayList<Present> gifts = new ArrayList<>();
            for (int j = 0; j < giftsArray.length(); j++) {
                JSONObject giftObject = giftsArray.getJSONObject(j);

                int giftId = giftObject.getInt("id");
                int wishlistId = giftObject.getInt("wishlist_id");
                String productUrl = giftObject.getString("product_url");
                int priority = giftObject.getInt("priority");
                int booked = giftObject.getInt("booked");

                Present present = new Present(giftId, wishlistId, productUrl, priority, booked, activity);
                gifts.add(present);
            }

            String creationDateString = wishlistObject.getString("creation_date");
            LocalDateTime creationDate;
            try {
                creationDate = LocalDateTime.parse(creationDateString, formatter);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Error parsing creation_date: " + creationDateString, e);
            }

            String endDateString = wishlistObject.getString("end_date");
            LocalDateTime endDate = null;
            if (endDateString != null && !endDateString.isEmpty() && !endDateString.equals("null")) {
                try {
                    endDate = LocalDateTime.parse(endDateString, formatter);
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Error parsing end_date: " + endDateString, e);
                }
            }

            Wishlist wishlist = new Wishlist(id, name, description, userId, gifts, creationDate, endDate);
            wishAdapter.addWishlist(wishlist);
        }

    }
}

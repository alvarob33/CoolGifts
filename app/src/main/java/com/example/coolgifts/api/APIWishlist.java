package com.example.coolgifts.api;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.coolgifts.RegisterActivity;
import com.example.coolgifts.presents.Present;
import com.example.coolgifts.wishlists.Wishlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class APIWishlist {

    public static ArrayList<Wishlist> getWishlistsFromCurrentUser(Activity activity) {

        //Obtenemos token del usuario registrado
        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Wishlist> wishlists = new ArrayList<>();
        //Peticion
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,"https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/" +loginToken.getId()+ "/wishlists", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", response.toString());
                        try {
                            ArrayList<Wishlist> parsedWishlists = parseWishlistsFromJSON(response, activity);
                            wishlists.addAll(parsedWishlists);
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
        queue.add(jor);
        return wishlists;
    }


    public static ArrayList<Wishlist> parseWishlistsFromJSON(JSONObject jsonObject, Activity activity) throws JSONException {
        ArrayList<Wishlist> wishlists = new ArrayList<>();

        JSONArray wishlistArray = jsonObject.getJSONArray("wishlist");
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
                boolean booked = giftObject.getBoolean("booked");

                Present present = new Present(giftId, wishlistId, productUrl, priority, booked, activity);
                gifts.add(present);
            }

            LocalDateTime creationDate = LocalDateTime.parse(wishlistObject.getString("creation_date"));
            LocalDateTime endDate = LocalDateTime.parse(wishlistObject.getString("end_date"));

            Wishlist wishlist = new Wishlist(id, name, description, userId, gifts, creationDate, endDate);
            wishlists.add(wishlist);
        }

        return wishlists;
    }
}

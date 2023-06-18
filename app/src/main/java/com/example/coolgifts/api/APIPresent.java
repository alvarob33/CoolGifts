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
import com.example.coolgifts.presents.Present;
import com.example.coolgifts.presents.PresentsAdapter;
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

public class APIPresent {

    public static void setProductAtributes(Present present, String productUrl, Activity activity) {

        //Obtenemos token del usuario registrado
        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        //Peticion
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,productUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", response.toString());
                        try {
                            present.setName(response.getString("name"));
                            present.setDescription(response.getString("description"));
                            present.setLink(response.getString("link"));
                            present.setPhoto(response.getString("photo"));
                            present.setPrice(response.getDouble("price"));
                            JSONArray categoryIdsArray = response.getJSONArray("categoryIds");
                            int[] categoryIds = new int[categoryIdsArray.length()];
                            for (int i = 0; i < categoryIdsArray.length(); i++) {
                                categoryIds[i] = categoryIdsArray.getInt(i);
                            }
                            present.setCategoryIds(categoryIds);
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
    }

    public static void getProducts(Activity activity, PresentsAdapter presentsAdapter) {

        //Obtenemos token del usuario registrado
        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        //Peticion
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/products", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response: ", response.toString());
                        try {
                            parseProductsFromJSON(response, presentsAdapter, activity);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        //change activity
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

    private static void parseProductsFromJSON(JSONArray wishlistArray, PresentsAdapter presentsAdapter, Activity activity) throws JSONException {

        for (int i = 0; i < wishlistArray.length(); i++) {
            JSONObject productObject = wishlistArray.getJSONObject(i);

            int id = productObject.getInt("id");
            String name = productObject.getString("name");
            String description = productObject.getString("description");
            String link = productObject.getString("link");
            String photo = productObject.getString("photo");
            double price = productObject.getDouble("price");

            JSONArray categoryIdsArray = productObject.getJSONArray("categoryIds");
            int[] categoryIds = new int[categoryIdsArray.length()];
            for (int j = 0; j < categoryIdsArray.length(); j++) {
                categoryIds[j] = categoryIdsArray.getInt(j);
            }

            Present product = new Present(id, name, description, link, photo, price, categoryIds, activity);
            presentsAdapter.addPresent(product);
        }
    }
}

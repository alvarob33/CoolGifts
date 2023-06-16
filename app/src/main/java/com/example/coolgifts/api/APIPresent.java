package com.example.coolgifts.api;

import static com.example.coolgifts.api.APIWishlist.parseWishlistsFromJSON;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.coolgifts.LoginActivity;
import com.example.coolgifts.presents.Present;
import com.example.coolgifts.wishlists.Wishlist;

import org.json.JSONException;
import org.json.JSONObject;

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
                            present.setCategoryId(response.getInt("categoryIds"));
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

}

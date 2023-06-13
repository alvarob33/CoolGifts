package com.example.coolgifts.api;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.coolgifts.LoginActivity;
import com.example.coolgifts.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class APIUser {

 public static void login(String username, String password, AppCompatActivity loginActivity) {

     JSONObject credenciales = new JSONObject();
     try{
         credenciales.put("email", username);
         credenciales.put("password",password);
     }catch (Exception e){
        Log.e("Error","Error añadiendo al Json");
     }
     RequestQueue queue = Volley.newRequestQueue(loginActivity);
     JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST,"https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/login",credenciales,
     new Response.Listener<JSONObject>() {

         @Override
         public void onResponse(JSONObject response) {
             Log.d("Response: ", response.toString());
             //añadir guardado del token y inicio de la pagina principal de menus
         }
     }, new Response.ErrorListener(){

         @Override
         public void onErrorResponse(VolleyError error) {
             Log.e("error: ", error.getMessage());
         }
     });
     queue.add(jor);
 }
    public static void register(String username,String email, String password, RegisterActivity registerActivity) {

        JSONObject credenciales = new JSONObject();
        try{
            credenciales.put("name", username);
            credenciales.put("last_name", "");
            credenciales.put("email", email);
            credenciales.put("password",password);
            credenciales.put("image","");
        }catch (Exception e){
            Log.e("Error","Error añadiendo al Json");
        }
        RequestQueue queue = Volley.newRequestQueue(registerActivity);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST,"https://balandrau.salle.url.edu/i3/socialgift/api/v1/users",credenciales,

                response -> {
                    Log.d("Response: ", response.toString());
                    String email1;
                    String password1;
                    try {
                        email1 = (String) response.get("email");
                        password1 = (String) response.get("password");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    login(email1, password1,registerActivity);
                },

                error -> Log.e("error: ", error.getMessage()));

        queue.add(jor);
    }
}


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
import com.example.coolgifts.LoginActivity;
import com.example.coolgifts.PerfilActivity;
import com.example.coolgifts.RegisterActivity;
import com.example.coolgifts.users.User;
import com.example.coolgifts.users.FriendAdapter;
import com.example.coolgifts.users.UserAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class APIUser {

 public static void login(String username, String password, LoginActivity loginActivity) {

     JSONObject credenciales = new JSONObject();
     try{
         credenciales.put("email", username);
         credenciales.put("password",password);
     }catch (Exception e){
        Log.e("Error","Error añadiendo al Json");
     }
     RequestQueue queue = Volley.newRequestQueue(loginActivity);
     JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST,"https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/login",credenciales,

             response -> {
                 Log.d("Response: ", response.toString());
                 //Guardado del token
                 try {
                     LoginToken.saveToken((String) response.get("accessToken"));
                 } catch (JSONException e) {
                     throw new RuntimeException(e);
                 }
                 //Inicio Menu
                 loginActivity.startMenu();
             },
             error ->{
                 int statusCode = error.networkResponse.statusCode;
                 if(statusCode == 401){
                    loginActivity.setTextMail();
                    loginActivity.setTextMail();
                 }else{
                     Log.e("error: ", error.getMessage());
                 }
             });
     queue.add(jor);
 }

    public static void loginAfterRegist(String username, String password, RegisterActivity registerActivity) {

        JSONObject credenciales = new JSONObject();
        try{
            credenciales.put("email", username);
            credenciales.put("password",password);
        }catch (Exception e){
            Log.e("Error","Error añadiendo al Json");
        }
        RequestQueue queue = Volley.newRequestQueue(registerActivity);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST,"https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/login",credenciales,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", response.toString());
                        //Guardado del token
                        try {
                            LoginToken.saveToken((String) response.get("accessToken"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        //Inicio Menu
                        registerActivity.startMenu();
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
                    String email2;
                    String password2;
                    try {
                        email2 = (String) response.get("email");
                        password2 = (String) response.get("password");
                        loginAfterRegist(email2,password2,registerActivity);
                    } catch (JSONException e) {

                        throw new RuntimeException(e);
                    }
                },

                error -> {
                    int statusCode = error.networkResponse.statusCode;
                    if (statusCode == 409) {
                        registerActivity.setEmailEditText();
                    } else {
                        Log.e("error: ", error.getMessage());
                    }
                });
        queue.add(jor);
    }

    public static void getFriendsFromCurrentUser(Activity activity, FriendAdapter friendAdapter) {

        //Obtenemos token del usuario registrado
        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        //Peticion
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/" + loginToken.getId() + "/friends", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response: ", response.toString());
                        try {
                            parseFriendsFromJSON(response, friendAdapter);
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

    private static void parseFriendsFromJSON(JSONArray wishlistArray, FriendAdapter friendAdapter) throws JSONException {

        for (int i = 0; i < wishlistArray.length(); i++) {
            JSONObject wishlistObject = wishlistArray.getJSONObject(i);

            int id = wishlistObject.getInt("id");
            String name = wishlistObject.getString("name");
            String email = wishlistObject.getString("email");
            String image = wishlistObject.getString("image");


            User user = new User(id, name, email, image);

            friendAdapter.addFriend(user);
        }

    }

    public static void getUserWithId(int id, PerfilActivity activity) {

        //Obtenemos token del usuario registrado
        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        //Peticion
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,"https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/" +id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", response.toString());
                        //Mostrar usuario
                        try {
                            activity.showUserData(parseUserFromJSON(response));
                        } catch (JSONException | ApiException e) {
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

    private static User parseUserFromJSON(JSONObject jsonUser) throws JSONException {

        int id = jsonUser.getInt("id");
        String name = jsonUser.getString("name");
        String email = jsonUser.getString("email");
        String image = jsonUser.getString("image");

        return new User(id, name, email, image);
    }
    public static void getUsersByString(String texto, Activity activity, UserAdapter adapter) {

        //Obtenemos token del usuario registrado
        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        //Peticion
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/search?s=" +texto, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response: ", response.toString());

                        try {
                            parseUsersFromJSON(response, adapter);
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

    private static void parseUsersFromJSON(JSONArray usersArray, UserAdapter userAdapter) throws JSONException {

        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject wishlistObject = usersArray.getJSONObject(i);

            int id = wishlistObject.getInt("id");
            String name = wishlistObject.getString("name");
            String email = wishlistObject.getString("email");
            String image = wishlistObject.getString("image");


            users.add( new User(id, name, email, image) );

        }
        userAdapter.setUsers(users);

    }
    public static void modifUser(String username,String email, String password, Activity activity) {

        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

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
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.PUT,"https://balandrau.salle.url.edu/i3/socialgift/api/v1/users",credenciales,

                response -> {
                    Log.d("Response: ", response.toString());

                },

                error -> {
                    int statusCode = error.networkResponse.statusCode;
                    if (statusCode == 409) {
                    } else {
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


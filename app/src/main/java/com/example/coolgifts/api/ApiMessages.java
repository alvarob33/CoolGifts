package com.example.coolgifts.api;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.coolgifts.MessageAdapter;
import com.example.coolgifts.users.FriendRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiMessages {

    public static void setMessages(Activity activity, int userId, MessageAdapter messageAdapter) {

        //Obtenemos token del usuario registrado
        LoginToken loginToken;
        try {
            loginToken = LoginToken.getInstance();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        //Peticion
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://balandrau.salle.url.edu/i3/socialgift/api/v1/messages/" + userId, null,
        new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response: ", response.toString());
                try {
//aqí parsear los mensajes de salida
//esta función recibe un usuario ID y retorna unstring json con todos los mensajes y la id de quien lo escribio, recomiendo diferenciar entre usuario logeado y amigo para mostrar por pantalla
                    parseMessagesFromJSON(response, messageAdapter);
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

    private static void parseMessagesFromJSON(JSONArray arrayMessages, MessageAdapter messageAdapter) throws JSONException {
        ArrayList<String> messages = new ArrayList<>();

        for (int i = 0; i < arrayMessages.length(); i++) {
            JSONObject message = arrayMessages.getJSONObject(i);

            String messageContent = message.getString("content");
            messages.add(messageContent);
        }
        messageAdapter.setMessages(messages);
    }
}

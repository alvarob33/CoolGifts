package com.example.coolgifts.api;

import android.os.AsyncTask;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

public class CreateUserTask extends AsyncTask<Void, Void, Response> {
    private JSONObject requestBody;

    public CreateUserTask(JSONObject requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    protected Response doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://balandrau.salle.url.edu/i3/socialgift/api/v1/users")
                .post(body)
                .build();

        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Response response) {
        // Aquí puedes manejar la respuesta de la API según tus necesidades
        if (response != null) {
            // Procesar la respuesta exitosa
        } else {
            // Manejar el caso de error de red
        }
    }
}


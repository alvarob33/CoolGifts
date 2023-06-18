package com.example.coolgifts.api;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoginToken {

    private static LoginToken sLoginToken;

    private String token;
    private int id;

    public static void saveToken(String token) {
        sLoginToken = new LoginToken(token);

        //return sLoginToken;
    }

    public static LoginToken getInstance() throws ApiException {
        if (sLoginToken == null) {
            throw new ApiException("No hay ningun usuario logeado.", null);
        }
        return sLoginToken;
    }

    private LoginToken(String token) {
        this.token = token;
        DecodedJWT jwt = JWT.decode(token);
        this.id = jwt.getClaim("id").asInt();
    }

    public String getToken() {
        return token;
    }

    public int getId() {
        return id;
    }

    public void logOut() {
        token = null;
        id = -1;
    }
}

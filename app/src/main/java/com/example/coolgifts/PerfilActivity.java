package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coolgifts.BasicActivity;
import com.example.coolgifts.MainActivity;
import com.example.coolgifts.MenuActivity;
import com.example.coolgifts.R;
import com.example.coolgifts.api.ApiException;
import com.example.coolgifts.api.LoginToken;
import com.example.coolgifts.users.User;

public class PerfilActivity extends AppCompatActivity {

    public static final String ID_USUARIO = "ID_USUARIO";
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        userId = getIntent().getIntExtra(ID_USUARIO, -1);

    }


    public void showUserData(User user) throws ApiException {
        //mostrar informacion de user por la activity
        if (user.getId() == -1 || user.getId() == LoginToken.getInstance().getId()) {
            Intent MenuIntent = new Intent(this, MenuActivity.class);
            startActivity(MenuIntent);
        } else {
            //el usuario no es el logueado
        }
    }
    public void onClickHome(View view) {
        Intent MenuIntent = new Intent(this, MenuActivity.class);
        startActivity(MenuIntent);
    }

}
package com.example.coolgifts.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coolgifts.BasicActivity;
import com.example.coolgifts.MainActivity;
import com.example.coolgifts.MenuActivity;
import com.example.coolgifts.R;
import com.example.coolgifts.users.User;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


    }


    public void showUserData(User user) {
        //mostrar informacion de user por la activity
    }
    public void onClickHome(View view) {
        Intent MenuIntent = new Intent(this, MenuActivity.class);
        startActivity(MenuIntent);
    }

}
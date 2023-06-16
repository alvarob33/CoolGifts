package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void onImageButtonFriendsClick(View view) {
        // Quan es selecciona la opcio Amistats (Friends)
        Intent intent = new Intent(MenuActivity.this, FriendsActivity.class);
        startActivity(intent);
    }


    public void onImageButtonGiftClick(View view) {
        // Quan es selecciona la opcio Regals (WishList)
        Intent intent = new Intent(MenuActivity.this, WishListActivity.class);
        startActivity(intent);
    }

    public void onImageButtonMessageClick(View view) {
        // Quan es selecciona la opcio Missatges (Message)
        Intent intent = new Intent(MenuActivity.this, MessagesActivity.class);
        startActivity(intent);
    }

    public void onImageButtonSearchClick(View view) {
        // Quan es selecciona la opcio Cercar (Search)
        Intent intent = new Intent(MenuActivity.this, SearchActivity.class);
        startActivity(intent);
    }


}
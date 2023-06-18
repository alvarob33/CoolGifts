package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {


    ImageButton ibGift;
    ImageButton ibFriends;
    ImageButton ibMessage;
    ImageButton ibSearch;
    ImageButton ibProfile_Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ibGift = (ImageButton) findViewById(R.id.ibGift);
        ibFriends = (ImageButton)findViewById(R.id.ibFriends);
        ibMessage = (ImageButton) findViewById(R.id.ibMessage);
        ibSearch = (ImageButton) findViewById(R.id.ibSearch);
        ibProfile_Menu = (ImageButton) findViewById(R.id.ibUser_Menu);


        ibGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quan es selecciona la opcio Regals (WishList)
                Intent intent = new Intent(MenuActivity.this, WishListActivity.class);
                intent.putExtra(WishListActivity.INTENT_USER_ID, WishListActivity.LOGGED_USER);
                startActivity(intent);

            }
        });

        ibFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quan es selecciona la opcio Amistats (Friends)
                Intent intent = new Intent(MenuActivity.this, FriendsActivity.class);
                startActivity(intent);

            }
        });

        ibMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quan es selecciona la opcio Missatges (Message)
                Intent intent = new Intent(MenuActivity.this, MessagesActivity.class);
                startActivity(intent);

            }
        });

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quan es selecciona la opcio Cercar (Search)
                Intent intent = new Intent(MenuActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });

        ibProfile_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quan es selecciona la opcio Cercar (Search)
                Intent intent = new Intent(MenuActivity.this, PerfilActivity.class);
                intent.putExtra(PerfilActivity.ID_USUARIO, -1);
                startActivity(intent);

            }
        });

    }






}
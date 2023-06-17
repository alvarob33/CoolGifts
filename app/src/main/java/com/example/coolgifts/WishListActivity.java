package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coolgifts.api.APIWishlist;
import com.example.coolgifts.wishlists.WishAdapter;
import com.example.coolgifts.wishlists.Wishlist;

import java.util.ArrayList;

public class WishListActivity extends AppCompatActivity {

    public static final String INTENT_USER_ID = "USER_ID";
    public static final int LOGGED_USER = -1;

    private RecyclerView wishListRecyclerView;
    private Button btnNewWishlist;


    private WishAdapter wishAdapter;
    private ArrayList<Wishlist> wishlists;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        wishListRecyclerView = findViewById(R.id.wishListRecyclerView);
        btnNewWishlist = findViewById(R.id.btnNewWishlist);

        //comprobamos si la lista de deseos es del usuario logueado o de otro usuario
        int userId = getIntent().getIntExtra(INTENT_USER_ID, -1);
        if (userId == LOGGED_USER) {
            //Hacemos el boton de anadir wishlist visible
            btnNewWishlist.setVisibility(View.VISIBLE);
            btnNewWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Quan es selecciona la opcio Amistats (Friends)
                    Intent intent = new Intent(WishListActivity.this, CreateWishlistActivity.class);
                    startActivity(intent);

                }
            });

            ArrayList<Wishlist> wishlists = new ArrayList<>();
            //Creamos adapter
            wishAdapter = new WishAdapter(wishlists, true, this);
            //Obtenemos wishlists
            APIWishlist.getWishlistsFromCurrentUser(wishlists, this, wishAdapter);


        } else {
            //Hacemos el boton de anadir wishlist invisible
            btnNewWishlist.setVisibility(View.INVISIBLE);

            ArrayList<Wishlist> wishlists = new ArrayList<>();
            //Creamos adapter
            wishAdapter = new WishAdapter(wishlists, false, this);
            //Obtenemos wishlists
            APIWishlist.getWishlistsFromUser(userId, wishlists, this, wishAdapter);

        }

        wishListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        wishListRecyclerView.setAdapter(wishAdapter);

    }
}
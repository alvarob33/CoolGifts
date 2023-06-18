package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coolgifts.api.APIWishlist;
import com.example.coolgifts.api.ApiException;
import com.example.coolgifts.api.LoginToken;
import com.example.coolgifts.wishlists.WishAdapter;
import com.example.coolgifts.wishlists.Wishlist;

import java.util.ArrayList;

public class WishListActivity extends AppCompatActivity {

    public static final String INTENT_USER_ID = "USER_ID";
    public static final int LOGGED_USER = -1;

    private RecyclerView wishListRecyclerView;
    private Button btnNewWishlist;


    private static WishAdapter wishAdapter;
    private ArrayList<Wishlist> wishlists;

    private static int userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        wishListRecyclerView = findViewById(R.id.wishListRecyclerView);
        btnNewWishlist = findViewById(R.id.btnNewWishlist);

        //comprobamos si la lista de deseos es del usuario logueado o de otro usuario
        userId = getIntent().getIntExtra(INTENT_USER_ID, -1);

        try {
            if (userId == LOGGED_USER || userId == LoginToken.getInstance().getId()) {
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
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        wishListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        wishListRecyclerView.setAdapter(wishAdapter);

    }

    public static int getUserId() {
        return userId;
    }

    public static WishAdapter getWishAdapter() {
        return wishAdapter;
    }

    public void goHome(View view) {
        Intent goHome = new Intent(this, MenuActivity.class);
        startActivity(goHome);
    }


}
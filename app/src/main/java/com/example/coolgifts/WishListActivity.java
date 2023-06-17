package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
            //Obtenemos wishlists
            wishlists = APIWishlist.getWishlistsFromCurrentUser(this);


            wishAdapter = new WishAdapter(wishlists, true, this);
        } else {
            //Hacemos el boton de anadir wishlist invisible
            btnNewWishlist.setVisibility(View.INVISIBLE);
            //Obtenemos wishlists
            wishlists = APIWishlist.getWishlistsFromUser(userId, this);

            wishAdapter = new WishAdapter(wishlists, false, this);
        }

        wishListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        wishListRecyclerView.setAdapter(wishAdapter);

    }
}
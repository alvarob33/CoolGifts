package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coolgifts.api.ApiException;
import com.example.coolgifts.api.LoginToken;
import com.example.coolgifts.presents.Present;
import com.example.coolgifts.presents.PresentsAdapter;
import com.example.coolgifts.wishlists.WishAdapter;
import com.example.coolgifts.wishlists.Wishlist;

import java.util.ArrayList;

public class PresentsActivity extends AppCompatActivity {

    private static Wishlist wishlist;

    private TextView tvNombreLista;
    private RecyclerView presentsRecycleView;
    private Button btnNewPresent;

    private static PresentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presents);

        tvNombreLista = (TextView) findViewById(R.id.tvNombreLista);
        presentsRecycleView = (RecyclerView) findViewById(R.id.presentsRecycleView);
        btnNewPresent = (Button) findViewById(R.id.btnNewPresent);

        //ajustamos nombre wishlist
        tvNombreLista.setText(getIntent().getStringExtra(WishAdapter.WISHLIST_NAME));

        //comprobamos si la lista es del usuario loggeado
        if (getIntent().getBooleanExtra(WishAdapter.IS_LOGGED_USER, true)) {
            adapter = new PresentsAdapter(PresentsAdapter.EDITABLE, wishlist.getPresents(), this);
        } else {
            adapter = new PresentsAdapter(PresentsAdapter.NO_EDITABLE, wishlist.getPresents(), this);
        }

        presentsRecycleView.setLayoutManager(new LinearLayoutManager(this));
        presentsRecycleView.setAdapter(adapter);

        try {
            if (WishListActivity.getUserId() == WishListActivity.LOGGED_USER || WishListActivity.getUserId() == LoginToken.getInstance().getId()) {

                btnNewPresent.setVisibility(View.VISIBLE);
                btnNewPresent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PresentsActivity.this, ProductsActivity.class);
                        startActivity(intent);

                    }
                });
            } else {
                btnNewPresent.setVisibility(View.INVISIBLE);
            }
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        //Configuramos recycle view
        updateUI();
    }

    private void updateUI() {

        adapter.notifyDataSetChanged();

    }

    public static Wishlist getWishlist() { return wishlist; }

    public static void setWishlist(Wishlist wishlist) {
        PresentsActivity.wishlist = wishlist;
    }

    public static PresentsAdapter getAdapter() {
        return adapter;
    }

    public void goHome(View view) {
        Intent goHome = new Intent(this, MenuActivity.class);
        startActivity(goHome);
    }
    public void goPerfil(View view) {
        // Quan es selecciona la opcio Cercar (Search)
        Intent intent = new Intent(this, PerfilActivity.class);
        intent.putExtra(PerfilActivity.ID_USUARIO, -1);
        startActivity(intent);

    }
}
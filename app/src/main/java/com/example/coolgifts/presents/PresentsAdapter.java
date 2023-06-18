package com.example.coolgifts.presents;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coolgifts.PresentsActivity;
import com.example.coolgifts.api.APIWishlist;
import com.example.coolgifts.wishlists.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class PresentsAdapter extends RecyclerView.Adapter<PresentsHolder>{

    public final static int NO_EDITABLE = 1;
    public final static int EDITABLE = 2;
    public final static int PRODUCTS = 3;
    private int type;
    private ArrayList<Present> lPresent;
    private Activity activity;

    public PresentsAdapter(int type, ArrayList<Present> lPresent, Activity activity) {
        this.type = type;
        this.lPresent = lPresent;
        this.activity = activity;
    }
    @Override
    public PresentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new PresentsHolder(type, layoutInflater, parent, activity);
    }
    @Override
    public void onBindViewHolder(PresentsHolder holder, int position) {
        Present present = lPresent.get(position);

        switch (type) {
            case PresentsAdapter.NO_EDITABLE:
                holder.getBtnDeletePresent().setVisibility(View.INVISIBLE);
                holder.getIbAddPresent().setVisibility(View.INVISIBLE);
                break;

            case PresentsAdapter.EDITABLE:
                holder.getIbAddPresent().setVisibility(View.INVISIBLE);
                //configuramos boton eliminar
                holder.getBtnDeletePresent().setVisibility(View.VISIBLE);
                holder.getBtnDeletePresent().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Quan es selecciona la opcio Regals (WishList)

                        //TODO: que se elimine el present de la wishlist
                    /*Intent intent = new Intent(MenuActivity.this, WishListActivity.class);
                    intent.putExtra(WishListActivity.INTENT_USER_ID, WishListActivity.LOGGED_USER);
                    startActivity(intent);*/

                    }
                });
                break;

            case PresentsAdapter.PRODUCTS:
                holder.getBtnDeletePresent().setVisibility(View.INVISIBLE);
                holder.getIbAddPresent().setVisibility(View.VISIBLE);
                holder.getIbAddPresent().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Quan es selecciona la opcio Regals (WishList)

                        //TODO: que se anada el present a la wishlist
                        present.setWishlistId(PresentsActivity.getWishlist().getId());
                        addPresent(present);
                        //TODO: que se anada bien en la api
                        APIWishlist.editarWishlist(PresentsActivity.getWishlist(), activity);


                    }
                });
                break;
        }
        holder.bind(present);
    }
    @Override
    public int getItemCount() {
        return lPresent.size();
    }

    public void addPresent(Present present) {
        lPresent.add(present);
        notifyDataSetChanged();
    }

    public void deletePresent() {

    }
}

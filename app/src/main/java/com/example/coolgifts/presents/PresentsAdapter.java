package com.example.coolgifts.presents;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coolgifts.PresentsActivity;
import com.example.coolgifts.WishViewActivity;
import com.example.coolgifts.api.APIPresent;
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
        holder.setIvPhoto(present.getPhoto());
        switch (type) {
            case PresentsAdapter.NO_EDITABLE:
                holder.getBtnDeletePresent().setVisibility(View.INVISIBLE);
                holder.getIbAddPresent().setVisibility(View.INVISIBLE);

                if (present.isBooked()) {
                    holder.getbReserve().setVisibility(View.INVISIBLE);
                } else {

                    holder.getbReserve().setVisibility(View.VISIBLE);
                    holder.getbReserve().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            APIPresent.createBook(present.getId(), activity);
                            holder.getbReserve().setVisibility(View.INVISIBLE);
                            present.setBooked(1);


                        }
                    });
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Obtener la posición del elemento clicado
                        WishViewActivity.setPresent(present);
                        Intent intent = new Intent(activity, WishViewActivity.class);
                        activity.startActivity(intent);

                    }
                });
                break;

            case PresentsAdapter.EDITABLE:
                holder.getIbAddPresent().setVisibility(View.INVISIBLE);
                holder.getbReserve().setVisibility(View.INVISIBLE);

                //configuramos boton eliminar
                holder.getBtnDeletePresent().setVisibility(View.VISIBLE);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Obtener la posición del elemento clicado
                        WishViewActivity.setPresent(present);
                        Intent intent = new Intent(activity, WishViewActivity.class);
                        activity.startActivity(intent);

                    }
                });
                holder.getBtnDeletePresent().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        APIPresent.deleteGift(present.getId(), activity);
                        PresentsActivity.getAdapter().deletePresent(present);

                    }
                });
                break;

            case PresentsAdapter.PRODUCTS:
                holder.getBtnDeletePresent().setVisibility(View.INVISIBLE);
                holder.getbReserve().setVisibility(View.INVISIBLE);
                holder.getIbAddPresent().setVisibility(View.VISIBLE);
                holder.getIbAddPresent().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        present.setWishlistId(PresentsActivity.getWishlist().getId());
                        PresentsActivity.getAdapter().addPresent(present);
                        APIPresent.createGift(PresentsActivity.getWishlist().getId(), present.getProductUrl(), activity);

                        // Mostrar el mensaje de éxito
                        Toast.makeText(activity, "Successfull: \nPresent added successfully to the Wishlist!", Toast.LENGTH_LONG).show();
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

    public void deletePresent(Present present) {
        lPresent.remove(present);
        notifyDataSetChanged();

    }
}

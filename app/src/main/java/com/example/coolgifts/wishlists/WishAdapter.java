package com.example.coolgifts.wishlists;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolgifts.PresentsActivity;
import com.example.coolgifts.R;
import com.example.coolgifts.WishListActivity;
import com.example.coolgifts.api.APIWishlist;

import java.util.ArrayList;

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.WishViewHolder> {

    public final static String WISHLIST_NAME = "WISHLIST_NAME";
    public final static String IS_LOGGED_USER = "IS_LOGGED_USER";
    private ArrayList<Wishlist> wishlists;
    private Context context;
    private boolean isLoggedUser;
    private WishListActivity activityContext;

    public WishAdapter(ArrayList<Wishlist> wishlists, boolean isLoggedUser, Context context, WishListActivity activity) {
        this.wishlists = wishlists;
        this.isLoggedUser = isLoggedUser;
        this.context = context;
        this.activityContext = activity;
    }

    @NonNull
    @Override
    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wish_list_item, parent, false);
        return new WishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishViewHolder holder, int position) {
        final Wishlist wishlist = wishlists.get(position);

        //Ajustar nombre de cada wishlist
        holder.title.setText(wishlist.getName());

        //Ajustar click en una wishlist
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pasar los regalos a la otra activity
                PresentsActivity.setWishlist(wishlist);

                // Obtener la posición del elemento clicado
                Intent intent = new Intent(activityContext, PresentsActivity.class);
                intent.putExtra(WISHLIST_NAME, wishlist.getName());
                intent.putExtra(IS_LOGGED_USER, isLoggedUser);
                activityContext.startActivity(intent);

            }
        });

        //Ajustar click en eliminar wishlist
        if (isLoggedUser) {
            holder.deleteIcon.setVisibility(View.VISIBLE);

            // Set an onClickListener for the delete icon
            holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Remove the wish from the wishlist
                    wishlists.remove(wishlist);
                    notifyDataSetChanged();
                    //Eliminar de la API
                    APIWishlist.deleteWishlist(wishlist.getId(),activityContext);
                    // Notify the adapter that the data set has changed

                }
            });
        } else {
            holder.deleteIcon.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return wishlists.size();
    }

    public static class WishViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView deleteIcon;

        public WishViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            deleteIcon = itemView.findViewById(R.id.delete_icon);
        }

    }

    public void addWishlist(Wishlist wishlist) {
        wishlists.add(wishlist);
        notifyDataSetChanged();
    }
}

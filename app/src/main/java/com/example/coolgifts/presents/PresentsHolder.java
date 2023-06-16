package com.example.coolgifts.presents;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coolgifts.R;

public class PresentsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Present present;
    private TextView tvName;
    private TextView tvPrice;
    private ImageView ivPhoto;
    private Activity activity;
    public PresentsHolder(LayoutInflater inflater, ViewGroup parent, Activity
            activity) {
        super(inflater.inflate(R.layout.list_element_present, parent, false));

        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvPrice = (TextView)itemView.findViewById(R.id.tvPrice);
        ivPhoto = (ImageView)itemView.findViewById(R.id.ivPresent);
        this.activity = activity;
        itemView.setOnClickListener(this);  //establecer que todo el elemento es el istener

    }
    public void bind(Present present) {
        this.present = present;
        tvName.setText(present.getName());
        tvPrice.setText(present.getPrice() + " €");

    }

    @Override
    public void onClick(View view) {
        //TODO: enviar a pagina de detalles del producto

        /*Intent i = new Intent(view.getContext(), DetailActivity.class);
        i.putExtra("POKEMON_NAME", pokemon.getName());

        activity.startActivityForResult(i, 1);*/
        //activity.moveDetailActivity
    }
}

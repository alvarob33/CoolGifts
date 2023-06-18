package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.coolgifts.presents.Present;
import com.example.coolgifts.presents.PresentsAdapter;
import com.example.coolgifts.wishlists.WishAdapter;

import java.util.ArrayList;
import java.util.List;

public class PresentsActivity extends AppCompatActivity {

    private static ArrayList<Present> presents;

    private TextView tvNombreLista;
    private RecyclerView presentsRecycleView;

    private PresentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presents);

        tvNombreLista = (TextView) findViewById(R.id.tvNombreLista);
        presentsRecycleView = (RecyclerView) findViewById(R.id.presentsRecycleView);

        //ajustamos nombre wishlist
        tvNombreLista.setText(getIntent().getStringExtra(WishAdapter.WISHLIST_NAME));

        //Configuramos recycle view
        updateUI();
    }

    private void updateUI() {
        //pokedex = Pokedex.getInstance();

        if (adapter == null) {
            adapter = new PresentsAdapter(presents, this);
            presentsRecycleView.setLayoutManager(new LinearLayoutManager(this));
            presentsRecycleView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public static ArrayList<Present> getPresents() { return presents; }

    public static void setPresents(ArrayList<Present> presents) {
        PresentsActivity.presents = presents;
    }
}
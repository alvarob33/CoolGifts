package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coolgifts.api.APIPresent;
import com.example.coolgifts.presents.Present;
import com.example.coolgifts.presents.PresentsAdapter;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView presentsRecycleView;
    private PresentsAdapter adapter;

    private ArrayList<Present> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        presentsRecycleView = (RecyclerView) findViewById(R.id.presentsRecycleView);

        //Configuramos recycle view
        updateUI();
    }

    private void updateUI() {
        //pokedex = Pokedex.getInstance();

        if (adapter == null) {
            products = new ArrayList<>();
            adapter = new PresentsAdapter(PresentsAdapter.PRODUCTS, products, this);
            presentsRecycleView.setLayoutManager(new LinearLayoutManager(this));
            presentsRecycleView.setAdapter(adapter);
            //anadimos los productos
            APIPresent.getProducts(this, adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
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
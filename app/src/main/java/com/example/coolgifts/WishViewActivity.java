package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coolgifts.api.APIPresent;
import com.example.coolgifts.presents.Present;
import com.squareup.picasso.Picasso;

public class WishViewActivity extends AppCompatActivity {


    private ImageView fotoProducto;
    private TextView name;
    private TextView price;
    private TextView description;
    private Button btnReserve;
    private static Present present;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_view);
        fotoProducto = findViewById(R.id.imageView13);
        name = findViewById(R.id.tvGiftName);
        price = findViewById(R.id.tvGiftPrice);
        description = findViewById(R.id.tvGiftDescription);
        btnReserve = findViewById(R.id.btn_reserve);

        name.setText(present.getName());
        price.setText(present.getPrice() + " €");
        description.setText(present.getDescription());
        Picasso.get().load(present.getPhoto()).into(fotoProducto);
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtener la posición del elemento clicado
                //APIPresent.createBook(present.getId(), WishViewActivity.this);
                btnReserve.setVisibility(View.INVISIBLE);

            }
        });

        //no se como cambiar la foto, se ha de pedir a la lista de productos
        //nombre.setText(new_text);
        //price.setText(new_price);
        //description(new_description);
    }

    public static void setPresent(Present present) {
        WishViewActivity.present = present;
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
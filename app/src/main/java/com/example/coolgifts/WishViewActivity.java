package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WishViewActivity extends AppCompatActivity {


    ImageView fotoProducto;
    TextView nombre;
    TextView price;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_view);
        fotoProducto = findViewById(R.id.imageView13);
        nombre = findViewById(R.id.textViewTitolObjecte);
        price = findViewById(R.id.textViewPrice);
        description = findViewById(R.id.textView4);
        //no se como cambiar la foto, se ha de pedir a la lista de productos
        //nombre.setText(new_text);
        //price.setText(new_price);
        //description(new_description);
    }

}
package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coolgifts.api.APIWishlist;
import com.example.coolgifts.wishlists.Wishlist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateWishlistActivity extends AppCompatActivity {

    private EditText etWishlistName;
    private EditText etWishlistDate;
    private Button btnNewWishlist;
    private LocalDateTime parsedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wishlist);

        etWishlistName = (EditText) findViewById(R.id.etWishlistName);
        etWishlistDate = (EditText) findViewById(R.id.etWishlistDate);
        btnNewWishlist = (Button) findViewById(R.id.btnNewWishlist);


        etWishlistDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    LocalDateTime currentDateTime = LocalDateTime.now();

                    // Crear un DatePickerDialog para seleccionar la fecha
                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateWishlistActivity.this, (view, year, month, dayOfMonth) -> {
                        // Obtener la fecha seleccionada
                        LocalDateTime selectedDateTime = LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0);

                        // Establecer la fecha seleccionada en el EditText
                        etWishlistDate.setText(selectedDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                        // Guardar la fecha seleccionada en parsedDate
                        parsedDate = selectedDateTime;

                    }, currentDateTime.getYear(), currentDateTime.getMonthValue() - 1, currentDateTime.getDayOfMonth());

                    // Mostrar el DatePickerDialog
                    datePickerDialog.show();
                }
            }
        });

        btnNewWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear Wishlist
                Wishlist newWishlist = new Wishlist(etWishlistName.getText().toString(), parsedDate);
                APIWishlist.createWishlist(newWishlist, CreateWishlistActivity.this);
                WishListActivity.getWishAdapter().addWishlist(newWishlist);
                finish();

            }
        });

    }
}
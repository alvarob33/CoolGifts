package com.example.coolgifts;

import static com.example.coolgifts.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coolgifts.api.ApiMessages;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    private RecyclerView RecyclerView;
    private MessageAdapter messageAdapter;

    TextInputEditText texto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_messages);
        RecyclerView = findViewById(id.recyclerView);

        // Generate the friend list
        List<String> messageList = new ArrayList<>();

        // Initialize the RecyclerView and the Adapter
        messageAdapter = new MessageAdapter(messageList);

        // Set the RecyclerView's layout manager and adapter
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.setAdapter(messageAdapter);

        //Cargar usuarios de la api
        ApiMessages.setMessages(this, PresentsActivity.getWishlist().getUserId(), messageAdapter);
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
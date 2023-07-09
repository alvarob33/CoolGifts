package com.example.coolgifts;

import static com.example.coolgifts.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.coolgifts.api.APIUser;
import com.example.coolgifts.users.User;
import com.example.coolgifts.users.UserAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextInputEditText texto;
    private List<User> userList;
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_messages);
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
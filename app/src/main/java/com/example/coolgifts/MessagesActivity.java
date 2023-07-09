package com.example.coolgifts;

import static com.example.coolgifts.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

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
        texto = findViewById(id.WriteBar);
        userAdapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(userAdapter);
        recyclerView = findViewById(id.recyclerView);
        setContentView(layout.activity_messages);
        texto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Cambiar usuarios por los que encuentre la api
                APIUser.getUsersByString(texto.toString(), (Activity) texto.getContext(),userAdapter);
                //searchUser(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
}
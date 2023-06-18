package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.coolgifts.api.APIUser;
import com.example.coolgifts.users.User;
import com.example.coolgifts.users.UserAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList, this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        TextInputEditText searchEditText = findViewById(R.id.WriteBar);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Cambiar usuarios por los que encuentre la api
                APIUser.getUsersByString(s.toString(), (Activity) searchEditText.getContext(), userAdapter);
                //searchUser(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }



}
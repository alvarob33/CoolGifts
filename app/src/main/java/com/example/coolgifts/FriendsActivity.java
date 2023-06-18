package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.coolgifts.api.APIUser;
import com.example.coolgifts.users.User;
import com.example.coolgifts.users.FriendAdapter;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView friendsRecyclerView;
    private FriendAdapter friendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        friendsRecyclerView = findViewById(R.id.friendsRecyclerView);


        // Generate the friend list
        List<User> userList = new ArrayList<>();

        // Initialize the RecyclerView and the Adapter
        friendAdapter = new FriendAdapter(userList, this);

        // Set the RecyclerView's layout manager and adapter
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendsRecyclerView.setAdapter(friendAdapter);

        //Cargar usuarios de la api
        APIUser.getFriendsFromCurrentUser(this, friendAdapter);
    }


}
package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coolgifts.api.APIFriends;
import com.example.coolgifts.users.FriendRequest;
import com.example.coolgifts.users.FriendRequestsAdapter;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FriendRequestsAdapter adapter;
    private ArrayList<FriendRequest> friendRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_requests);

        recyclerView = findViewById(R.id.rv_friend_requests);
        friendRequests = new ArrayList<>(); // Hay que reemplazarlo por la lista de friend requests
        adapter = new FriendRequestsAdapter(friendRequests, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        APIFriends.getFriendRequest(adapter, this);
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
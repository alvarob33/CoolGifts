package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coolgifts.api.APIUser;
import com.example.coolgifts.users.User;
import com.example.coolgifts.users.FriendAdapter;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView friendsRecyclerView;
    private FriendAdapter friendAdapter;
    private ImageButton ibFriendReqs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        friendsRecyclerView = findViewById(R.id.friendsRecyclerView);
        ibFriendReqs = findViewById(R.id.ibFriendRequests);

        // Generate the friend list
        List<User> userList = new ArrayList<>();

        // Initialize the RecyclerView and the Adapter
        friendAdapter = new FriendAdapter(userList, this);

        // Set the RecyclerView's layout manager and adapter
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendsRecyclerView.setAdapter(friendAdapter);

        //Cargar usuarios de la api
        APIUser.getFriendsFromCurrentUser(this, friendAdapter);

        ibFriendReqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quan es selecciona la opcio Regals (WishList)
                Intent intent = new Intent(FriendsActivity.this, FriendRequestsActivity.class);
                startActivity(intent);

            }
        });
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
package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.coolgifts.api.ApiMessages;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private List<String> messages;
    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messages = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageAdapter(messages);
        recyclerView.setAdapter(adapter);

        //Esta es la configuración del swipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        loadNewMessages();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Lógica que se usa pra la actualización de mensajes
                loadNewMessages();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    private void loadNewMessages() {

        ApiMessages.setMessages(this, getIntent().getIntExtra(PerfilActivity.ID_USUARIO, 0), adapter);
    }


}
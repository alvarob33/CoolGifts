package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Lógica que se usa pra la actualización de mensajes
                loadNewMessages();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //He añadido un pequeño ejemplo
        messages.add("Hey");
        messages.add("¿Que taal?");
        adapter.notifyDataSetChanged();
    }

    private void loadNewMessages() {
        // Se obtienen los nuevos mensajes de la fuente de datos
        List<String> newMessages = getNewMessages();

        // Se agregan los nuevos mensajes a la lista existente
        messages.addAll(0, newMessages);

        //Cuando se han insertado nuevos elementos en la parte superior de la lista se notifica al adapter
        adapter.notifyItemRangeInserted(0, newMessages.size());
    }

    private List<String> getNewMessages() {
        //Ejemplo de como se obtnien nuevos mensajes a partir de la fuente de datos
        List<String> newMessages = new ArrayList<>();
        newMessages.add("Nuevo mensaje 1");
        newMessages.add("Nuevo mensaje 2");
        return newMessages;
    }
}
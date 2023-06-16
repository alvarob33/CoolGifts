package com.example.coolgifts.presents;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PresentsAdapter extends RecyclerView.Adapter<PresentsHolder>{
    private ArrayList<Present> lPresent;
    private Activity activity;

    public PresentsAdapter(ArrayList<Present> lPresent, Activity activity) {
        this.lPresent = lPresent;
        this.activity = activity;
    }
    @Override
    public PresentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new PresentsHolder(layoutInflater, parent, activity);
    }
    @Override
    public void onBindViewHolder(PresentsHolder holder, int position) {
        Present pokemon = lPresent.get(position);
        holder.bind(pokemon);
    }
    @Override
    public int getItemCount() {
        return lPresent.size();
    }
}

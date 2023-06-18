package com.example.coolgifts.presents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coolgifts.PresentsActivity;
import com.example.coolgifts.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PresentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PresentsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView presentsRecycleView;
    private PresentsAdapter adapter;

    // TODO: Rename and change types of parameters

    public PresentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PresentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PresentsFragment newInstance(String param1, String param2) {
        PresentsFragment fragment = new PresentsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_presents, container, false);
        presentsRecycleView =(RecyclerView) v.findViewById(R.id.presentsRecycleView2);
        presentsRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;

    }

    //este metodo se activa cuando el fragment vuelve a ser visible
    //(el metodo onCreateView solo se activa cuando se crea el fragment por primera vez)
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        //pokedex = Pokedex.getInstance();
        ArrayList<Present> lPresent = PresentsActivity.getPresents();//pokedex.getPokemons();

        if (adapter == null) {
            adapter = new PresentsAdapter(lPresent, getActivity());
            presentsRecycleView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
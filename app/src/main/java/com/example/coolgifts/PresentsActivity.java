package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.coolgifts.presents.Present;

import java.util.ArrayList;
import java.util.List;

public class PresentsActivity extends AppCompatActivity {

    private static ArrayList<Present> presents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presents);
    }

    public static ArrayList<Present> getPresents() { return presents; }
}
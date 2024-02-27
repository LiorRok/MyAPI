package com.example.myapi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.myapi.R;
import com.example.myapi.models.States;
import com.example.myapi.services.DataServices;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recycle view code
        ArrayList<States> arrState = DataServices.getArrState();

//        for(States s : arrState){
//            Log.d("result", s.getFlag());
//        }

        // Initialize adapter with your data
        adapter = new CustomAdapter(arrState);

        // Set adapter to RecyclerView
        recyclerView = findViewById(R.id.res);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
}
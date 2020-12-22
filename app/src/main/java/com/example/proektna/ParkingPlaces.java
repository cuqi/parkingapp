package com.example.proektna;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class ParkingPlaces extends AppCompatActivity {
    RecyclerView mRecyclerView;
    myAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_places);


//        List<String> values = Arrays.asList("Skopje", "Tokyo", "Berlin", "London", "Denver", "Paris", "Oslo", "Helsinki");
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.list);
//
//        mRecyclerView.setHasFixedSize(true);
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        mAdapter = new myAdapter(values, R.layout.my_row, this);
//
//        mRecyclerView.setAdapter(mAdapter);
    }
}
package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class ViewLocationsActivity extends AppCompatActivity {

    RecyclerView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations_acitivity);
        listView = findViewById(R.id.locationsRecyclerView);
    }
}

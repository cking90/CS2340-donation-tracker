package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

import static edu.gatech.cs2340.nonprofitdonationtracker.controllers.Location.locationList;

public class ViewLocationsActivity extends AppCompatActivity {

    RecyclerView listView;
    private ArrayList<String> locationNames = new ArrayList<>();
    private ArrayList<String> locationAddresses = new ArrayList<>();
    private ArrayList<String> locationIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations_acitivity);
        listView = findViewById(R.id.locationsRecyclerView);
        Log.d("Help", locationList.toString());
        initLocationData();
    }

    private void initLocationData() {
        for (Location location : locationList) {
            locationNames.add(location.getName());
            locationAddresses.add(location.getFullAddress());
            locationIDs.add(Integer.toString(location.getKey()));
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d("initRecyclerView", "initRecycler: started");
        RecyclerView recyclerView = findViewById(R.id.locationsRecyclerView);
        LocationRecyclerViewAdapter adapter = new LocationRecyclerViewAdapter(locationNames,
                                                    locationAddresses, locationIDs, this);
        Log.d("initRecyclerView", "initRecycler: adapter instantiated");
        recyclerView.setAdapter(adapter);
        Log.d("initRecyclerView", "initRecycler: adapter Set");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("initRecyclerView", "initRecycler: layout Set");
    }

}

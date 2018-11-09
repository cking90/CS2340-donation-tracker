package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

/**
 * Provides the user with the list of all locations. Selecting
 * a location will bring the user to the ViewSingleLocationActivity
 */
public class ViewLocationsActivity extends AppCompatActivity {

    private final ArrayList<String> locationNames = new ArrayList<>();
    private final ArrayList<String> locationAddresses = new ArrayList<>();
    private final ArrayList<String> locationIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations_acitivity);
        RecyclerView listView = findViewById(R.id.locationsRecyclerView);
        initLocationData();
    }

    private void initLocationData() {
        for (Location location : Location.getLocationList()) {
            locationNames.add(location.getName());
            locationAddresses.add(location.getFullAddress());
            locationIDs.add(Integer.toString(location.getKey()));
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d("initRecyclerView", "initRecycler: started");
        RecyclerView recyclerView = findViewById(R.id.locationsRecyclerView);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        LocationRecyclerViewAdapter adapter = new LocationRecyclerViewAdapter(locationNames,
                                                    locationAddresses, locationIDs,
                                                extras.getString("user_type"), this);
        Log.d("initRecyclerView", "initRecycler: adapter instantiated");
        recyclerView.setAdapter(adapter);
        Log.d("initRecyclerView", "initRecycler: adapter Set");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        Log.d("initRecyclerView", "initRecycler: layout Set");
    }

}

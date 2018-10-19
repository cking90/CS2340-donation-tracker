package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

import static edu.gatech.cs2340.nonprofitdonationtracker.controllers.Location.locationList;

public class ViewAllDonationsActivity extends AppCompatActivity {

    RecyclerView listView;
    private ArrayList<String> donationNames = new ArrayList<>();
    private ArrayList<String> donationValues = new ArrayList<>();
    private ArrayList<Date> donationDates = new ArrayList<>();
    private int locationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        locationID = extras.getInt("location_id");

        setContentView(R.layout.activity_view_all_donations);
        listView = findViewById(R.id.donationsRecyclerView);

        initDonationData();
    }

    private void initDonationData() {
        for (Location l : locationList) {
            if (l.getKey() == locationID) {
                for (Donation d : l.getDonations()) {
                    donationNames.add(d.getName());
                    donationValues.add(String.format("$%.2f", d.getValue()));
                    donationDates.add(d.getDate());
                }
                return;
            }
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d("initRecyclerView", "initRecycler: started");
        RecyclerView recyclerView = findViewById(R.id.donationsRecyclerView);
        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(donationNames,
                donationValues, donationDates, this);
        Log.d("initRecyclerView", "initRecycler: adapter instantiated");
        recyclerView.setAdapter(adapter);
        Log.d("initRecyclerView", "initRecycler: adapter Set");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("initRecyclerView", "initRecycler: layout Set");
    }

}

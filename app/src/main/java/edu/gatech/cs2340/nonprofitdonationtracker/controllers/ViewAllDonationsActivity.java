package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

import static edu.gatech.cs2340.nonprofitdonationtracker.controllers.Location.locationList;

public class ViewAllDonationsActivity extends AppCompatActivity {

    RecyclerView listView;
    private ArrayList<String> donationNames = new ArrayList<>();
    private ArrayList<String> donationValues = new ArrayList<>();
    private ArrayList<Date> donationDates = new ArrayList<>();
    private ArrayList<String> donationShortDescriptions = new ArrayList<>();
    private ArrayList<String> donationLongDescriptions = new ArrayList<>();
    private ArrayList<String> donationCategories = new ArrayList<>();
    private int locationID;

    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = Model.getInstance();
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
                    donationValues.add(Float.toString(d.getValue()));
                    donationDates.add(d.getDate());
                    donationShortDescriptions.add(d.getShortDescription());
                    donationLongDescriptions.add(d.getLongDescription());
                    donationCategories.add(d.getCategory().toString());
                }
                break;
            }
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d("initDonateRecyclerView", "initRecycler: started");
        RecyclerView recyclerView = findViewById(R.id.donationsRecyclerView);
        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(donationNames,
                donationValues, donationDates, donationShortDescriptions, donationLongDescriptions, donationCategories, locationID, this);
        Log.d("initDonateRecyclerView", "initRecycler: adapter instantiated");
        recyclerView.setAdapter(adapter);
        Log.d("initDonateRecyclerView", "initRecycler: adapter Set");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        Log.d("initDonateRecyclerView", "initRecycler: layout Set");
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, ViewSingleLocationActivity.class);
        intent.putExtra("location_id", locationID);
        intent.putExtra("user_type", model.getCurrentUserType());
        startActivity(intent);
    }


}

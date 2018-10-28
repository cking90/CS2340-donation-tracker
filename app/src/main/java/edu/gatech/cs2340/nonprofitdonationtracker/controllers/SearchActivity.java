package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

import static edu.gatech.cs2340.nonprofitdonationtracker.controllers.Location.locationList;

public class SearchActivity extends AppCompatActivity {

    private List<Donation> donationList;
    RecyclerView listView;
    private ArrayList<String> donationNames = new ArrayList<>();
    private ArrayList<String> donationValues = new ArrayList<>();
    private ArrayList<Date> donationDates = new ArrayList<>();
    private ArrayList<String> donationShortDescriptions = new ArrayList<>();
    private ArrayList<String> donationLongDescriptions = new ArrayList<>();
    private ArrayList<String> donationCategories = new ArrayList<>();
    private ArrayList<Integer> locationIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        List<Location> locationList = new ArrayList<>();
        locationList.addAll(Location.getLocationList());
        Location defaultOption = new Location(-1, "All Locations", -1, -1, "", "", "", -1, null, -1, "");
        locationList.add(0, defaultOption);
        Spinner location_spinner =  (Spinner)findViewById(R.id.spinner_location_picker_id);
        location_spinner.setAdapter(new ArrayAdapter<Location>(this, android.R.layout.simple_spinner_item, locationList));

        Spinner category_picker = (Spinner)findViewById(R.id.spinner_category_id);
        List<Category> categories = Arrays.asList(Category.values());
        category_picker.setAdapter(new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, categories));

        listView = findViewById(R.id.donationsRecyclerView);
        initDonationData();

    }

    public void onClickSearchByCategory(View view) {
        Spinner location_picker = (Spinner)findViewById(R.id.spinner_location_picker_id);
        Location selectedLocation = (Location)location_picker.getSelectedItem();
        Spinner category_picker = (Spinner)findViewById(R.id.spinner_category_id);
        Category selected_category = (Category)category_picker.getSelectedItem();
        int location_key = selectedLocation.getKey();
        donationList = Location.filterByLocation(location_key);
        donationList = filterByCategory(selected_category);
    }

    public void onClickSearchByItem(View view) {
        Spinner location_picker = (Spinner)findViewById(R.id.spinner_location_picker_id);
        Location selectedLocation = (Location)location_picker.getSelectedItem();
        EditText searched_item = (EditText)findViewById(R.id.editText_searched_item_id);
        String item = searched_item.getText().toString();
        int location_key = selectedLocation.getKey();
        donationList = Location.filterByLocation(location_key);
        donationList = filterByItemName(item);
    }

    private List<Donation> filterByCategory(Category category) {
        List<Donation> updatedList = new ArrayList<>();
        for (Donation item : donationList) {
            if (item.getCategory().equals(category)) {
                updatedList.add(item);
            }
        }
        return updatedList;
    }

    private List<Donation> filterByItemName(String item) {
        List<Donation> updatedList = new ArrayList<>();
        for (Donation donation: donationList) {
            if (donation.getName().equalsIgnoreCase(item)) {
                updatedList.add(donation);
            }
        }
        return updatedList;
    }

    private void initDonationData() {
        for (Location l : locationList) {
            for (Donation d : l.getDonations()) {
                donationNames.add(d.getName());
                donationValues.add(Float.toString(d.getValue()));
                donationDates.add(d.getDate());
                donationShortDescriptions.add(d.getShortDescription());
                donationLongDescriptions.add(d.getLongDescription());
                donationCategories.add(d.getCategory().toString());
                locationIDs.add(l.getKey());
            }
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d("initDonateRecyclerView", "initRecycler: started");
        RecyclerView recyclerView = findViewById(R.id.donationsRecyclerView);
        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(donationNames,
                donationValues, donationDates, donationShortDescriptions, donationLongDescriptions, donationCategories
                , locationIDs, this);
        Log.d("initDonateRecyclerView", "initRecycler: adapter instantiated");
        recyclerView.setAdapter(adapter);
        Log.d("initDonateRecyclerView", "initRecycler: adapter Set");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        Log.d("initDonateRecyclerView", "initRecycler: layout Set");
    }

}

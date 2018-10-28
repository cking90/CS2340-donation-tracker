package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class SearchActivity extends AppCompatActivity {

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

    }




}

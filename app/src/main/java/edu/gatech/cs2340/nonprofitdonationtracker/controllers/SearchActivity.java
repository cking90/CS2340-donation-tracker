package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class SearchActivity extends AppCompatActivity {

    private List<Donation> donationList;

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

    public void onClickSearchByCategory(View view) {
        Spinner location_picker = (Spinner)findViewById(R.id.spinner_location_picker_id);
        Location selectedLocation = (Location)location_picker.getSelectedItem();
        Spinner category_picker = (Spinner)findViewById(R.id.spinner_category_id);
        Category selected_category = (Category)category_picker.getSelectedItem();
        int location_key = selectedLocation.getKey();
        //List<Donation> filteredList = Location.filterByLocation(location_key);
    }

    public void onClickSearchByItem(View view) {
        Spinner location_picker = (Spinner)findViewById(R.id.spinner_location_picker_id);
        Location selectedLocation = (Location)location_picker.getSelectedItem();
        EditText searched_item = (EditText)findViewById(R.id.editText_searched_item_id);
        String item = searched_item.getText().toString();
        int location_key = selectedLocation.getKey();
        //List<Donation> filteredList = Location.filterByLocation(location_key);
    }

    //public void filterByCategory()

}

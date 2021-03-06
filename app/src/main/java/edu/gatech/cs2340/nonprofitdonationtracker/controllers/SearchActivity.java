package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Category;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Donation;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Location;

/**
 * Controller for the activity_search layout file that
 * allows the user to search through the donations across
 * all locations.
 */
public class SearchActivity extends AppCompatActivity {

    private Map<Integer, List<Donation>> donationList;
    private RecyclerView recyclerView;
    private final ArrayList<String> donationNames = new ArrayList<>();
    private final ArrayList<String> donationValues = new ArrayList<>();
    private final ArrayList<Date> donationDates = new ArrayList<>();
    private final ArrayList<String> donationShortDescriptions = new ArrayList<>();
    private final ArrayList<String> donationLongDescriptions = new ArrayList<>();
    private final ArrayList<String> donationCategories = new ArrayList<>();
    private final ArrayList<Integer> locationIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        List<Location> locationList = new ArrayList<>(Location.getLocationList());
        Location defaultOption = new Location(
                -1, "All Locations", -1, -1, "", "",
                "", -1, null, -1, "");
        locationList.add(0, defaultOption);
        Spinner location_spinner = findViewById(R.id.spinner_location_picker_id);
        location_spinner.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, locationList));

        Spinner category_picker = findViewById(R.id.spinner_category_id);
        List<Category> categories = Arrays.asList(Category.values());
        category_picker.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categories));

        recyclerView = findViewById(R.id.donationsRecyclerView);
        initDonationData();

    }

    /**
     * searches the donations by its category
     *
     * does so by getting a list of donations by location and then filters
     * them by the category attribute
     *
     * @param view current view
     */
    public void onClickSearchByCategory(View view) {
        Spinner location_picker = findViewById(R.id.spinner_location_picker_id);
        Location selectedLocation = (Location)location_picker.getSelectedItem();
        Spinner category_picker = findViewById(R.id.spinner_category_id);
        Category selected_category = (Category)category_picker.getSelectedItem();
        int location_key = selectedLocation.getKey();
        donationList = Location.filterByLocation(location_key);
        filterByCategory(selected_category);
        updateRecyclerView();
    }

    /**
     * searches the donations by its name
     *
     * does so by getting a list of donations by location and then filters
     * them by the item name attribute
     *
     * @param view current  view
     */
    public void onClickSearchByItem(View view) {
        Spinner location_picker = findViewById(R.id.spinner_location_picker_id);
        Location selectedLocation = (Location)location_picker.getSelectedItem();
        EditText searched_item = findViewById(R.id.editText_searched_item_id);

        Editable itemField = searched_item.getText();
        String item = itemField.toString();
        int location_key = selectedLocation.getKey();
        donationList = Location.filterByLocation(location_key);
        filterByItemName(item);
        updateRecyclerView();
    }

    private void filterByCategory(Category category) {
        Map<Integer, List<Donation>> updatedList = new HashMap<>();
        for (Integer key : donationList.keySet()) {
            for (Donation d : donationList.get(key)) {
                Category donationCategory = d.getCategory();
                if (donationCategory.equals(category)) {
                    if (!updatedList.containsKey(key)) {
                        List<Donation> donationArray = new ArrayList<>();
                        donationArray.add(d);
                        updatedList.put(key, donationArray);
                    } else {
                        List<Donation> donationList = updatedList.get(key);
                        donationList.add(d);
                    }
                }
            }
        }
        donationList = updatedList;
    }

    private void filterByItemName(String item) {
        Map<Integer, List<Donation>> updatedList = new HashMap<>();
        for (Integer key : donationList.keySet()) {
            for (Donation d : donationList.get(key)) {
                String donationName = d.getName();
                if (donationName.equals(item)) {
                    if (!updatedList.containsKey(key)) {
                        List<Donation> donationArray = new ArrayList<>();
                        donationArray.add(d);
                        updatedList.put(key, donationArray);
                    } else {
                        List<Donation> donationList = updatedList.get(key);
                        donationList.add(d);
                    }
                }
            }
        }
        donationList = updatedList;
    }

    private void initDonationData() {
        for (Location l : Location.getLocationList()) {
            for (Donation d : l.getDonations()) {
                donationNames.add(d.getName());
                donationValues.add(Float.toString(d.getValue()));
                donationDates.add(d.getDate());
                donationShortDescriptions.add(d.getShortDescription());
                donationLongDescriptions.add(d.getLongDescription());

                Category donationCategory = d.getCategory();
                donationCategories.add(donationCategory.toString());

                locationIDs.add(l.getKey());

            }
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d("initDonateRecyclerView", "initRecycler: started");
        recyclerView = findViewById(R.id.donationsRecyclerView);
        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(donationNames,
                donationValues, donationDates, donationShortDescriptions, donationLongDescriptions,
                donationCategories, locationIDs, this);
        Log.d("initDonateRecyclerView", "initRecycler: adapter instantiated");
        recyclerView.setAdapter(adapter);
        Log.d("initDonateRecyclerView", "initRecycler: adapter Set");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        Log.d("initDonateRecyclerView", "initRecycler: layout Set");
    }

    private void updateRecyclerView() {
        ArrayList<String> donationNames = new ArrayList<>();
        ArrayList<String> donationValues = new ArrayList<>();
        ArrayList<Date> donationDates = new ArrayList<>();
        ArrayList<String> donationShortDescriptions = new ArrayList<>();
        ArrayList<String> donationLongDescriptions = new ArrayList<>();
        ArrayList<String> donationCategories = new ArrayList<>();
        ArrayList<Integer> locationIDs = new ArrayList<>();
        for (Integer key : donationList.keySet()) {
            for (Donation d : donationList.get(key)) {
                donationNames.add(d.getName());
                donationValues.add(Float.toString(d.getValue()));
                donationDates.add(d.getDate());
                donationShortDescriptions.add(d.getShortDescription());
                donationLongDescriptions.add(d.getLongDescription());

                Category donationCategory = d.getCategory();
                donationCategories.add(donationCategory.toString());
                locationIDs.add(key);
            }
        }
        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(donationNames,
                donationValues, donationDates, donationShortDescriptions, donationLongDescriptions,
                donationCategories, locationIDs, this);
        Log.d("initDonateRecyclerView", "initRecycler: adapter instantiated");
        recyclerView.setAdapter(adapter);
        if (donationNames.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext()
                    , "No Items Matched Your Search", Toast.LENGTH_LONG);
            toast.show();
        }

    }

}

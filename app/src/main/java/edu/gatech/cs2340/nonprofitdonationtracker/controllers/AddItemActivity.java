package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.util.Log;
import android.widget.Spinner;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class AddItemActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText shortDescription;
    private EditText longDescription;
    private EditText price;
    private Spinner categorySpinner;

    int locationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Bundle extras = getIntent().getExtras();
        locationId = extras.getInt("location_id");

        nameField = (EditText) findViewById(R.id.itemName);
        shortDescription = (EditText) findViewById(R.id.shortDescription);
        longDescription = (EditText) findViewById(R.id.longDescription);
        price = (EditText) findViewById(R.id.price);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        ArrayAdapter<Category> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Category.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

    }

    public void onCancelPressed(View view) {
        //probably don't need this button because user can hit back button on phone
    }

    //Prem - unsure on how to add the new donation into the correct location list
    //feel like this is an easy fix for you charlie so I'm trying to finish the rest
    //also probably need to add implementation to prevent duplicate items
    public void onAddItemPressed(View view) {
        String nameField = this.nameField.getText().toString();
        String shortDescription = this.shortDescription.getText().toString();
        String longDescription = this.longDescription.getText().toString();
        Float price = Float.parseFloat(this.price.getText().toString());

        for (Location location: Location.locationList) {
            if (location.getKey() == locationId) {
                location.addDonation(new Donation(nameField, shortDescription,
                        longDescription, price, (Category) categorySpinner.getSelectedItem()));
            }
            for(Donation donation: location.getDonations()) {
                Log.d("tears", "" + donation);
            }
        }

    }
}

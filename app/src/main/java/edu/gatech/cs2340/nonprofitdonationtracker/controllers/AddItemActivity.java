package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class AddItemActivity extends AppCompatActivity {

    private String nameField;
    private String shortDescription;
    private String longDescription;
    private String price;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameField = ((EditText) findViewById(R.id.itemName)).toString();
        shortDescription = ((EditText) findViewById(R.id.shortDescription)).toString();
        longDescription = ((EditText) findViewById(R.id.longDescription)).toString();
        price = ((EditText) findViewById(R.id.price)).toString();

    }

    public void onCancelPressed(View view) {
        //probably don't need this button because user can hit back button on phone
    }

    //Prem - unsure on how to add the new donation into the correct location list
    //feel like this is an easy fix for you charlie so I'm trying to finish the rest
    //the category will be a spinner like the one in registration
    public void onAddItemPressed(View view) {
        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("location_id");
        for (Location location: Location.locationList) {
            if (location.getKey() == id) {
                location.addDonation(new Donation(nameField, shortDescription,
                        longDescription, Float.parseFloat(price), category));
            }
            for(Donation donation: location.getDonations()) {
                Log.d("donation", "" + donation);
            }
        }

    }
}

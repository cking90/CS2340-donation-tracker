package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class AddItemActivity extends AppCompatActivity {

    private EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameField = (EditText) findViewById(R.id.itemName);
    }

    public void onCancelPressed(View view) {

    }

    public void onAddItemPressed(View view) {
        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("location_id");
        for (Location location: Location.locationList) {
            if (location.getKey() == id) {
                location.addDonation(new Donation(nameField.toString(), null
                        , null, 0, null));
            }
            for(Donation donation: location.getDonations()) {
                Log.d("donation", "" + donation);
            }
        }

    }
}

package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class ViewSingleLocationActivity extends AppCompatActivity {

    TextView locationName;
    TextView locationAddress;
    TextView locationKey;
    TextView locationCoords;
    TextView locationWebsite;
    TextView locationPhoneNum;
    TextView locationType;
    int currLocationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_location);

        Bundle extras = getIntent().getExtras();
        currLocationID = extras.getInt("location_id");
        Location currLocation = Location.getLocationWithKey(currLocationID);

        locationName = findViewById(R.id.locationNameTextView);
        locationName.setText(currLocation.getName());

        locationKey = findViewById(R.id.locationIDTextView);
        locationKey.setText((Integer.toString(currLocation.getKey())));

        locationAddress = findViewById(R.id.locationAddressTextView);
        locationAddress.setText((currLocation.getFullAddress()));

        locationCoords = findViewById(R.id.locationCoordsTextView);
        locationCoords.setText(("Coordinates: " + currLocation.getCoordinates()));

        locationWebsite = findViewById(R.id.locationWebTextView);
        locationWebsite.setText(("Website: " + currLocation.getWebsite()));

        locationPhoneNum = findViewById(R.id.locationPhoneTextView);
        locationPhoneNum.setText(("Phone: " + Long.toString(currLocation.getPhoneNum())));

        locationType = findViewById(R.id.locationTypeTextView);
        locationType.setText(("Type: " + currLocation.getType()));

    }


    public void onClickAddItem(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras.getString("user_type").equals("Location Employee")) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("location_id", getIntent().getExtras().getInt("location_id"));
            intent.putExtra("user_type",extras.getString("user_type"));
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext()
                    , "Only Location Employees may add items", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickViewItems(View view) {
        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, ViewAllDonationsActivity.class);
        intent.putExtra("location_id", currLocationID);
        intent.putExtra("user_type", extras.getString("user_type"));
        Log.d("coconuts", Location.getLocationWithKey(currLocationID).toString());
        startActivity(intent);
    }
}

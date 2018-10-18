package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class ViewSingleLocationActivity extends AppCompatActivity {

    TextView locationName;
    TextView locationAddress;
    TextView locationKey;
    TextView locationCoords;
    TextView locationWebsite;
    TextView locationPhoneNum;
    TextView locationType;
    Location currLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_location);

        Bundle extras = getIntent().getExtras();
        currLocation = getCurrLocation(extras.getInt("location_id"));

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

    /**
     * Uses the passed in key to find out what location is
     * currently being queried
     */
    private Location getCurrLocation(int ID) {
        for (Location l : Location.locationList) {
            if (l.getKey() == ID) {
                return l;
            }
        }
        return null;
    }

    public void onClickViewItems(View view) {
        Intent intent = new Intent(this, ViewAllDonationsActivity.class);
        intent.putExtra("location_id", getIntent().getExtras().getInt("location_id"));
        startActivity(intent);
    }
}

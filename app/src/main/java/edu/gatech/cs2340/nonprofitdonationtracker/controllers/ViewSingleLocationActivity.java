package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Location;

/**
 * Provides users with the details of a single location. Location
 * Employee's can access the AddItemActivity from the associated
 * layout file. Users can also view the donations for the location
 */
public class  ViewSingleLocationActivity extends AppCompatActivity {

    private int currLocationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_location);

        TextView locationName;
        TextView locationAddress;
        TextView locationKey;
        TextView locationCoords;
        TextView locationWebsite;
        TextView locationPhoneNum;
        TextView locationType;

        Intent extrasIntent = getIntent();
        Bundle extras = extrasIntent.getExtras();
        assert extras != null;
        currLocationID = extras.getInt("location_id");

        Location currLocation = Location.getLocationWithKey(currLocationID);
        assert currLocation != null;

        locationName = findViewById(R.id.locationNameTextView);

        String currLocationName = currLocation.getName();
        locationName.setText(currLocationName);

        locationKey = findViewById(R.id.locationIDTextView);
        int currLocationKey = currLocation.getKey();
        locationKey.setText((Integer.toString(currLocationKey)));

        locationAddress = findViewById(R.id.locationAddressTextView);
        String currLocationFullAdress = currLocation.getFullAddress();
        locationAddress.setText(currLocationFullAdress);

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
     * Controller for the Add Item button on the associated
     * layout file. Checks to confirm that the user
     * is of type Location Employee before proceeding to
     * the AddItemActivity
     *
     * @param view Add Item Button
     */
    public void onClickAddItem(View view) {
        Intent extrasIntent = getIntent();
        Bundle extras = extrasIntent.getExtras();
        assert extras != null;
        String userType = extras.getString("user_type");

        if ("Location Employee".equals(userType)) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("location_id", Objects.requireNonNull(
                    getIntent().getExtras()).getInt("location_id"));
            intent.putExtra("user_type",extras.getString("user_type"));
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext()
                    , "Only Location Employees may add items", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Controller for the View Items (Donations) button.
     * Allows the user to view all the donations help
     * within the location
     *
     * @param view the view items button
     */
    public void onClickViewItems(View view) {
        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, ViewAllDonationsActivity.class);
        intent.putExtra("location_id", currLocationID);
        intent.putExtra("user_type", Objects.requireNonNull(extras).getString("user_type"));
        Log.d("coconuts", Objects.requireNonNull(
                Location.getLocationWithKey(currLocationID)).toString());
        startActivity(intent);
    }

    /**
     * Brings the user back to the home page
     * @param view the back button
     */
    public void onClickBack(View view) {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

}

package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Model;

/**
 * Controller for the layout displayed when the user
 * successfully signs into the application. Currently
 * provides immediate access to viewing all locations
 * and searching through all donations.
 *
 */
public class HomePageActivity extends AppCompatActivity {

    private String userEmail;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        model = Model.getInstance();
        userEmail = model.getCurrentUserEmail();

    }

    public void onClickAddLocation(View view) {
        Intent extrasIntent = getIntent();
        Bundle extras = extrasIntent.getExtras();
        assert extras != null;
        String userType = extras.getString("user_type");
        if ("Manager".equals(userType)) {
            Intent intent = new Intent(this, AddLocationActivity.class);
            intent.putExtra("user_type", extras.getString("user_type"));
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext()
                    , "Only Managers may add locations", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    /**
     * Brings the user to the ViewAllLocations Activity and
     * passes the user's email and type to the next activity.
     * Such information is now deprecated, as the current user
     * is stored within the Model class
     *
     * @param view the activity_home_page layout file
     */
    public void onClickViewLocations(View view) {
        Intent intent = new Intent(this, ViewLocationsActivity.class);
        intent.putExtra("user_email", userEmail);
        intent.putExtra("user_type", model.getCurrentUserType());
        startActivity(intent);
    }

    /**
     * Brings the user to the Graph Activity and
     *
     * @param view the activity_home_page layout file
     */
    public void onClickViewGraphs(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }

    /**
     * the on click method for a button which opens MapsActivity
     * @param view - view
     */
    public void onClickViewOnMaps (View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /**
     * Returns the user to the OpeningScreen activity, where
     * they can register or sign back in with a different account.
     *
     * @param view the activity_home_page layout file
     */
    public void onClickLogout(View view) {
        Intent intent = new Intent(this, OpeningScreen.class);
        startActivity(intent);
    }

    /**
     * Brings the user to the SearchDonationActivity, where
     * they can filter through donations across all locations.
     *
     * @param view the activity_home_page layout file
     */
    public void onClickSearchDonations(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}

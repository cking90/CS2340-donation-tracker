package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import edu.gatech.cs2340.nonprofitdonationtracker.R;

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

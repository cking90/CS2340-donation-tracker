package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

/**
 * Controller for the view in which users can add
 * an item (donation) to a specific location. Only
 * Users of type LocationEmployee will be granted
 * access to this activity
 */
public class AddItemActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText shortDescription;
    private EditText longDescription;
    private EditText price;
    private Spinner categorySpinner;

    private int locationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        locationId = extras.getInt("location_id");

        nameField = findViewById(R.id.itemName);
        shortDescription = findViewById(R.id.shortDescription);
        longDescription = findViewById(R.id.longDescription);
        price = findViewById(R.id.price);
        categorySpinner = findViewById(R.id.categorySpinner);

        ArrayAdapter<Category> adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item, Category.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

    }

    /**
     * Controller for the cancel button displayed in the
     * activity_add_item layout. Returns to the ViewSingleLocation
     * Activity and passes back the location id of the current
     * locations and the user_type of the current user.
     *
     * Note: user_type is deprecated and no longer used. Current
     * user is now stored in the Model.
     * @param view the layout file containing the cancel button,
     *             as described in the method description.
     */
    public void onCancelPressed(View view) {
        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, ViewSingleLocationActivity.class);
        intent.putExtra("location_id", getIntent().getExtras().getInt("location_id"));
        intent.putExtra("user_type",extras.getString("user_type"));
        startActivity(intent);
    }

    /**
     * Pulls information from the activity_add_item layout and
     * adds the item to the donationList instance variable of the
     * current location.
     *
     * @param view the layout file as described in the
     *             method description.
     */
    public void onAddItemPressed(View view) {
        String nameField = this.nameField.getText().toString();
        String shortDescription = this.shortDescription.getText().toString();
        String longDescription = this.longDescription.getText().toString();
        Float price = Float.parseFloat(this.price.getText().toString());
        Donation d = new Donation(nameField, shortDescription,
                longDescription, price, (Category) categorySpinner.getSelectedItem());
        Location.addDonationToLocation(locationId, d);

        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, ViewSingleLocationActivity.class);
        intent.putExtra("location_id", getIntent().getExtras().getInt("location_id"));
        intent.putExtra("user_type",extras.getString("user_type"));
        startActivity(intent);
    }
}

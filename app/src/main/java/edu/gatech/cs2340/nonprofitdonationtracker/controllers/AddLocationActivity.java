package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Category;
import edu.gatech.cs2340.nonprofitdonationtracker.models.LocCategory;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Location;
import edu.gatech.cs2340.nonprofitdonationtracker.models.LocationType;

public class AddLocationActivity extends AppCompatActivity {

    private int key;
    private EditText name;
    private EditText latitude;
    private EditText longitude;
    private EditText street;
    private EditText city;
    private EditText state;
    private EditText zip;
    private EditText phonenum;
    private EditText website;
    private Spinner catSpinner;
    //location has key, name, lat, long, street, city, state
    // zipcode, type, phonenum, website and
    //DonationList (init. null)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        key = 0;
        while (Location.getLocationWithKey(key) != null) {
            key++;
        }

        name = findViewById(R.id.Name);
        latitude = findViewById(R.id.Latitude);
        longitude = findViewById(R.id.Longitude);
        street = findViewById(R.id.Street);
        city = findViewById(R.id.City);
        state = findViewById(R.id.State);
        zip = findViewById(R.id.Zip);
        phonenum = findViewById(R.id.Phone);
        website = findViewById(R.id.Website);
        catSpinner = findViewById(R.id.catSpinner);

        ArrayAdapter<LocCategory> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, LocCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapter);
    }

    public void onCancelPressed(View view) {
        Intent extrasIntent = getIntent();
        Bundle extras = extrasIntent.getExtras();

        assert extras != null;
        String userType = extras.getString("user_type");
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.putExtra("user_type", userType);
        startActivity(intent);
    }

    public void onAddLocationPressed(View view) {
        Editable nameField = this.name.getText();
        String nameString = nameField.toString();

        Editable latField = this.latitude.getText();
        Double latDouble = Double.parseDouble(latField.toString());

        Editable longField = this.longitude.getText();
        Double longDouble = Double.parseDouble(longField.toString());

        Editable streetField = this.street.getText();
        String streetString = streetField.toString();

        Editable cityField = this.city.getText();
        String cityString = cityField.toString();

        Editable stateField = this.state.getText();
        String stateString = stateField.toString();

        Editable zipField = this.zip.getText();
        Integer zipInt = Integer.parseInt(zipField.toString());

        Editable phoneField = this.phonenum.getText();
        Long phoneLong = Long.parseLong(phoneField.toString());

        Editable webField = this.website.getText();
        String webString = webField.toString();

        Location l = new Location(key, nameString, latDouble, longDouble,
                streetString, cityString, stateString, zipInt, (LocationType) catSpinner.getSelectedItem(),
                phoneLong, webString);

        Location.addLocation(l);

        Intent passedIntent = getIntent();
        Bundle extras = passedIntent.getExtras();

        assert extras != null;
        String userType = extras.getString("user_type");

        Intent intent = new Intent(this, HomePageActivity.class);
        intent.putExtra("user_type", userType);
        startActivity(intent);
    }

}

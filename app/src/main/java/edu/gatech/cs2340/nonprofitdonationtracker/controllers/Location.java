package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.nio.charset.StandardCharsets;

/**
 * Created by Charlie on 10/10/2018.
 *
 * Stores the blueprint to create additional locations.
 * Contains a list of all current locations.
 * Contains static methods for reading in the Location
 * csv file.
 */
public class Location {

    public static final Set<Location> locationList = new HashSet<>();

    private int key;
    private String name;
    private double latitude;
    private double longitude;
    private String street;
    private String city;
    private String state;
    private int zipcode;
    private LocationType type;
    private long phoneNum;
    private String website;
    private List<Donation> donationList = new ArrayList<Donation>();

    private static DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    /**
     * Standardized order of the Location data
     * in the csv file
     */
    private static final int KEY_INDEX = 0, NAME_INDEX = 1, LAT_INDEX = 2,
                        LONG_INDEX = 3, STREET_INDEX = 4, CITY_INDEX = 5,
                        STATE_INDEX = 6, ZIP_INDEX = 7, TYPE_INDEX = 8,
                        PHONE_INDEX = 9, URL_INDEX = 10;

    public Location(int key, String name, double latitude, double longitude,
                        String street, String city, String state, int zipcode,
                        LocationType type, long phoneNum, String website) {
        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.type = type;
        this.phoneNum = phoneNum;
        this.website = website;
    }

    public static void addLocation(Location location) {
        locationList.add(location);
        database.child("locations").child(Integer.toString(location.getKey())).child("key").setValue(location.getKey());
        database.child("locations").child(Integer.toString(location.getKey())).child("name").setValue(location.getName());
        database.child("locations").child(Integer.toString(location.getKey())).child("latitude").setValue(location.getLatitude());
        database.child("locations").child(Integer.toString(location.getKey())).child("longitude").setValue(location.getLongitude());
        database.child("locations").child(Integer.toString(location.getKey())).child("street").setValue(location.getStreet());
        database.child("locations").child(Integer.toString(location.getKey())).child("city").setValue(location.getCity());
        database.child("locations").child(Integer.toString(location.getKey())).child("state").setValue(location.getState());
        database.child("locations").child(Integer.toString(location.getKey())).child("zipcode").setValue(location.getZipcode());
        database.child("locations").child(Integer.toString(location.getKey())).child("type").setValue(location.getType());
        database.child("locations").child(Integer.toString(location.getKey())).child("phone").setValue(location.getPhoneNum());
        database.child("locations").child(Integer.toString(location.getKey())).child("website").setValue(location.getWebsite());
//        DatabaseReference locationRef = database.child("locations");
//        for (Donation d : location.donationList) {
//            locationRef.child("donations").child(d.getDate().toString()).child("date").setValue(d.getDate());
//            locationRef.child("donations").child(d.getDate().toString()).child("name").setValue(d.getName());
//            locationRef.child("donations").child(d.getDate().toString()).child("shortDescription").setValue(d.getShortDescription());
//            locationRef.child("donations").child(d.getDate().toString()).child("longDescription").setValue(d.getLongDescription());
//            locationRef.child("donations").child(d.getDate().toString()).child("value").setValue(d.getValue());
//            locationRef.child("donations").child(d.getDate().toString()).child("category").setValue(d.getCategory());
//        }
    }

    public static void addLocationLocal(Location l) {
        locationList.add(l);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Location)) {
            return false;
        }
        Location otherLoc = (Location) other;
        return this.key == otherLoc.key;
    }

    @Override
    public String toString() {
        String msg = "";
        msg += "name: " + name  + ", ";
        msg += "key: " + this.key + ", ";
        msg += "address: " + this.getFullAddress();
        return msg;
    }

    @Override
    public int hashCode() {
        return this.key;
    }


    public static void parseCSV(InputStream is) {
        try {
            BufferedReader csvScan = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            csvScan.readLine();
            String line;
            while ((line = csvScan.readLine()) != null){
                String[] data = line.split(",");
                Log.d("CSVline", line);
                Location newLoc = new Location(Integer.parseInt(data[KEY_INDEX]), data[NAME_INDEX],
                        Double.parseDouble(data[LAT_INDEX]), Double.parseDouble(data[LONG_INDEX]),
                        data[STREET_INDEX], data[CITY_INDEX], data[STATE_INDEX],
                        Integer.parseInt(data[ZIP_INDEX]), LocationType.valueOf(data[TYPE_INDEX].replaceAll(" ", "").toUpperCase()),
                        Long.parseLong(data[PHONE_INDEX].replaceAll("[^0-9]", "")),
                        data[URL_INDEX]);
                Location.addLocation(newLoc);
            }
            csvScan.close();
        } catch (IOException e) {
            Log.d("CSVParseFail", e.toString());
        }


    }

    public int getKey() {
        return this.key;
    }

    public String getName(){
        return this.name;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getStreet() {
        return this.street;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public int getZipcode() {
        return this.zipcode;
    }

    public LocationType getType() {
        return this.type;
    }

    public long getPhoneNum() {
        return this.phoneNum;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getFullAddress() {
        return String.format("%s, %s, %s, %s",
                street, city, state, zipcode);
    }

    public String getCoordinates() {
        String coords = String.format("%.2f, %.2f", latitude, longitude);
        return coords;
    }

    public static String locationListToString() {
        String msg = "";
        for (Location l : locationList) {
            msg += l.toString() + "\n";
        }
        return msg;
    }

    public static Location getLocationWithKey(int key) {
        for (Location l : Location.locationList) {
            if (l.getKey() == key) {
                return l;
            }
        }
        return null;
    }

    public List<Donation> getDonations() {
        return this.donationList;
    }

    public void addDonation(Donation d) {
        donationList.add(d);

        DatabaseReference locationRef = FirebaseDatabase.getInstance().getReference()
                                                .child("locations").child(Integer.toString(this.getKey()));
        locationRef.child("donations").child(d.getDate().toString()).child("date").setValue(d.getDate().getTime());
        locationRef.child("donations").child(d.getDate().toString()).child("name").setValue(d.getName());
        locationRef.child("donations").child(d.getDate().toString()).child("shortDescription").setValue(d.getShortDescription());
        locationRef.child("donations").child(d.getDate().toString()).child("longDescription").setValue(d.getLongDescription());
        locationRef.child("donations").child(d.getDate().toString()).child("value").setValue(d.getValue());
        locationRef.child("donations").child(d.getDate().toString()).child("category").setValue(d.getCategory());
    }

    public void addDonationLocal(Donation d) {
        donationList.add(d);
    }

}

enum LocationType {
    DROPOFF, STORE, WAREHOUSE
}

package edu.gatech.cs2340.nonprofitdonationtracker.models;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

    private static final Set<Location> locationList = new HashSet<>();

    private final int key;
    private final String name;
    private final double latitude;
    private final double longitude;
    private final String street;
    private final String city;
    private final String state;
    private final int zipcode;
    private final LocationType type;
    private final long phoneNum;
    private final String website;
    private final List<Donation> donationList = new ArrayList<>();


    /**
     * Standardized order of the Location data
     * in the csv file
     */
    private static final int KEY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int LAT_INDEX = 2;
    private static final int LONG_INDEX = 3;
    private static final int STREET_INDEX = 4;
    private static final int CITY_INDEX = 5;
    private static final int STATE_INDEX = 6;
    private static final int ZIP_INDEX = 7;
    private static final int TYPE_INDEX = 8;
    private static final int PHONE_INDEX = 9;
    private static final int URL_INDEX = 10;

    /**
     * Constructor which creates a new location based on
     * passed in parameters
     *
     * @param key - unique location key
     * @param name - name of location
     * @param latitude - latitude
     * @param longitude - longitude
     * @param street - street address
     * @param city - city address
     * @param state - state
     * @param zipcode - zip code
     * @param type - type of location (enum)
     * @param phoneNum - phone number
     * @param website - website of location
     */
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

    public HashMap<Category, Float> getPercentMarkups() {
        HashMap<Category, Float> markups = new HashMap<>();
        float f = 0.1f;
        for (Category c : Category.values()) {
            Float fValue = new Float(((Math.random()*10) + 1)*f);
            markups.put(c, fValue);
        }
        return markups;
    }

    public float donationsPerMonthRate() {
        float[] vals = new float[12];
        for (Donation d: donationList) {
            int month = DonationInfo.getMonth(d.getDate());
            vals[month] += 1;
        }
        float total = 0;
        for (float f : vals) {
            total += f;
        }
        total = total/12;
        return total;
    }

    /**
     * Returns the list of locations
     * @return a list of locations
     */
    public static Set<Location> getLocationList() {
        return Collections.unmodifiableSet(locationList);
    }

    /**
     * Adds the passed in location to the local and
     * database data centers containing information about
     * all locations
     *
     * @param location to be added
     */
    public static void addLocation(Location location) {
        locationList.add(location);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("locations").child(Integer.toString(
                location.getKey())).child("key").setValue(location.getKey());
        database.child("locations").child(Integer.toString
                (location.getKey())).child("name").setValue(location.getName());
        database.child("locations").child(Integer.toString(
                location.getKey())).child("latitude").setValue(location.getLatitude());
        database.child("locations").child(Integer.toString(
                location.getKey())).child("longitude").setValue(location.getLongitude());
        database.child("locations").child(Integer.toString(
                location.getKey())).child("street").setValue(location.getStreet());
        database.child("locations").child(Integer.toString(
                location.getKey())).child("city").setValue(location.getCity());
        database.child("locations").child(Integer.toString(
                location.getKey())).child("state").setValue(location.getState());
        database.child("locations").child(Integer.toString(
                location.getKey())).child("zipcode").setValue(location.getZipcode());
        database.child("locations").child(Integer.toString(
                location.getKey())).child("type").setValue(location.getType());
        database.child("locations").child(Integer.toString(
                location.getKey())).child("phone").setValue(location.getPhoneNum());
        database.child("locations").child(Integer.toString(
                location.getKey())).child("website").setValue(location.getWebsite());
    }

    /**
     * Adds the passed in location to the local copy of
     * a list containing all locations
     * @param l location to be added to the local
     *          copy
     */
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
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.key;
    }

    /**
     * parses the input stream of an assumed format
     * to populated the location data
     * @param is input stream containing csv file
     *           with location data
     */
    public static void parseCSV(InputStream is) {
        try {
            BufferedReader csvScan = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8));
            csvScan.readLine();
            String line = csvScan.readLine();
            while (line != null){
                String[] data = line.split(",");
                Log.d("CSV line", line);
                String location = data[TYPE_INDEX].replaceAll(
                        " ", "");
                Location newLoc = new Location(Integer.parseInt(data[KEY_INDEX]), data[NAME_INDEX],
                        Double.parseDouble(data[LAT_INDEX]), Double.parseDouble(data[LONG_INDEX]),
                        data[STREET_INDEX], data[CITY_INDEX], data[STATE_INDEX],
                        Integer.parseInt(data[ZIP_INDEX]),
                        LocationType.valueOf(location.toUpperCase()),
                        Long.parseLong(
                                data[PHONE_INDEX].replaceAll("[^0-9]", "")),
                        data[URL_INDEX]);
                Location.addLocation(newLoc);
                line = csvScan.readLine();
            }
            csvScan.close();
        } catch (IOException e) {
            Log.d("CSVParseFail", e.toString());
        }


    }

    /**
     * gets the location's key
     * @return the key of the location
     */
    public int getKey() {
        return this.key;
    }

    /**
     * getter
     * @return String of the location's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * getter
     * @return the latitudinal position of
     * the location
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * getter
     * @return the longitudinal position
     * of the location
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * getter
     * @return String of the Street address
     * of the location
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * getter
     * @return String name of the city
     * in which the location is located
     */
    public String getCity() {
        return this.city;
    }

    /**
     * getter
     * @return String of the location's city
     */
    public String getState() {
        return this.state;
    }

    /**
     * getter
     * @return int of the location's zipcode
     */
    public int getZipcode() {
        return this.zipcode;
    }

    /**
     * Gets the type.
     *
     * @return     The type.
     */
    public LocationType getType() {
        return this.type;
    }

    /**
     * Gets the phone number.
     *
     * @return     The phone number.
     */
    public long getPhoneNum() {
        return this.phoneNum;
    }

    /**
     * Gets the website.
     *
     * @return     The website.
     */
    public String getWebsite() {
        return this.website;
    }

    /**
     * Gets the full address.
     *
     * @return     The full address.
     */
    public String getFullAddress() {
        return String.format("%s, %s, %s, %s",
                street, city, state, zipcode);
    }

    /**
     * Gets the coordinates.
     *
     * @return     The coordinates.
     */
    public String getCoordinates() {
        return String.format(Locale.US,"%.2f, %.2f", latitude, longitude);
    }

    /**
     * gives you the list of locations in a string format.
     *
     * @return a description of the locations
     */
    public static String locationListToString() {
        StringBuilder msg = new StringBuilder();
        for (Location l : locationList) {
            msg.append(l.toString());
            msg.append("\n");
        }
        return msg.toString();
    }

    /**
     * Gets the location using a key.
     *
     * @param      key   The key of the location you want
     *
     * @return     The location with that key.
     */
    public static Location getLocationWithKey(int key) {
        for (Location l : Location.locationList) {
            if (l != null) {
                if (l.getKey() == key) {
                    return l;
                }
            }
        }
        return null;
    }

    /**
     * Gets the donations.
     *
     * @return     The donations.
     */
    public List<Donation> getDonations() {
        return Collections.unmodifiableList(this.donationList);
    }

    /**
     * Adds a donation.
     *
     * @param      d     a donation to add
     */
    public void addDonation(Donation d) {
        donationList.add(d);
        DatabaseReference locationRef = FirebaseDatabase.getInstance().getReference().child(
                "locations").child(Integer.toString(this.getKey()));
        locationRef.child("donations").child(d.getDate().toString()).child(
                "date").setValue(d.getDate().getTime());
        locationRef.child("donations").child(d.getDate().toString()).child(
                "name").setValue(d.getName());
        locationRef.child("donations").child(d.getDate().toString()).child(
                "shortDescription").setValue(d.getShortDescription());
        locationRef.child("donations").child(d.getDate().toString()).child(
                "longDescription").setValue(d.getLongDescription());
        locationRef.child("donations").child(d.getDate().toString()).child(
                "value").setValue(d.getValue());
        locationRef.child("donations").child(d.getDate().toString()).child(
                "category").setValue(d.getCategory());
    }

    /**
     * Adds a donation to a local list.
     *
     * @param      d    the donation to add
     */
    public void addDonationLocal(Donation d) {
        donationList.add(d);
    }

    /**
     * Filters donations by location they are at
     * with only ones at a location w/input key shown
     * @param locationKey is the input key you want to see donations at.
     * @return Map<Integer, List<Donation>> all Donations at a location.
     */
    public static Map<Integer, List<Donation>> filterByLocation(int locationKey) {
        if (locationKey == -1) {
            Set<Location> locations = getLocationList();
            Map<Integer, List<Donation>> specificDonations = new HashMap<>();
            for (Location location : locations) {
                for (Donation donation : location.getDonations()) {
                    if (!specificDonations.containsKey(location.getKey())) {
                        List<Donation> donationArray = new ArrayList<>();
                        donationArray.add(donation);
                        specificDonations.put(location.getKey(), donationArray);
                    } else {
                        specificDonations.get(location.getKey()).add(donation);
                    }

                }
            }
            return specificDonations;
        } else {
            Set<Location> locations = getLocationList();
            Map<Integer, List<Donation>> specificDonations = new HashMap<>();
            for (Location location : locations) {
                if (location.getKey() == locationKey) {
                    for (Donation donation : location.getDonations()) {
                        if (!specificDonations.containsKey(location.getKey())) {
                            List<Donation> donationArray = new ArrayList<>();
                            donationArray.add(donation);
                            specificDonations.put(location.getKey(), donationArray);
                        } else {
                            specificDonations.get(location.getKey()).add(donation);
                        }
                    }
                }
            }
            return specificDonations;
        }
    }

    /**
     * Adds the donation to the proper location
     * @param locationId the location being added to
     * @param d the donation being added
     */
    public static void addDonationToLocation(int locationId, Donation d) {
        for (Location location: Location.getLocationList()) {
            if (location.getKey() == locationId) {
                location.addDonation(d);
            }
        }
    }

}


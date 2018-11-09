//Created by Prem

package edu.gatech.cs2340.nonprofitdonationtracker;

import org.junit.Test;

import edu.gatech.cs2340.nonprofitdonationtracker.controllers.Location;
import edu.gatech.cs2340.nonprofitdonationtracker.controllers.LocationType;

import static org.junit.Assert.assertEquals;

/**
 * Test to check for getting location, when inputting a given key
 */
public class getLocationWithKeyTest {
    @Test
    public void getLocationWithKey() {
        //no locations, testing for loop condition where it doesn't enter
        assertEquals(Location.getLocationWithKey(1), null);

        Location location1 = new Location(1, "name1", 20, 30,
                "street", "city", "state", 30308,
                LocationType.DROPOFF, 123, "website");

        Location location2 = new Location(2, "name2", 20, 30,
                "street", "city", "state", 30308,
                LocationType.STORE, 123, "website");

        Location location3 = null;

        Location.addLocationLocal(location1);
        Location.addLocationLocal(location2);
        Location.addLocationLocal(location3);

        //there are locations, key is valid
        assertEquals(Location.getLocationWithKey(2), location2);

        //there are locations, location is null
        assertEquals(Location.getLocationWithKey(3), null);

        //there are locations, key is not valid
        assertEquals(Location.getLocationWithKey(4), null);
    }
}

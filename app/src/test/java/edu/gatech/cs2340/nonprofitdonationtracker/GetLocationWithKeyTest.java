//Created by Prem

package edu.gatech.cs2340.nonprofitdonationtracker;

import org.junit.Test;

import edu.gatech.cs2340.nonprofitdonationtracker.models.Location;
import edu.gatech.cs2340.nonprofitdonationtracker.models.LocationType;

import static org.junit.Assert.assertEquals;

/**
 * @author prem
 * checks the location with key method
 */
public class GetLocationWithKeyTest {

    /**
     * checks the location with key method through different test conditions mentioned below
     */
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
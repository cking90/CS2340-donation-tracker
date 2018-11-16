package edu.gatech.cs2340.nonprofitdonationtracker;



import org.junit.Test;

import edu.gatech.cs2340.nonprofitdonationtracker.models.Location;
import edu.gatech.cs2340.nonprofitdonationtracker.models.LocationType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * @author charlie
 * test to check if two equal locations are recognized as equal
 */
public class LocationEqualsTest {

    /**
     * The test that checks if two equal locations are recognized as equal
     */
    @Test
    public void testEquals() {
        
        Location location1 = new Location(3, "Dummy Location", 12,
                                30, "5252 Baker Street", "Evans", "GA",
                                30809, LocationType.DROPOFF, 706750933,
                                "www.why.com");
        Location location2 = new Location(2, "Dummy Location #2", 52,
                60, "1294 Haulding Str", "PinkerTown", "GA",
                31119, LocationType.STORE, 12341231,
                "www.cause.com");

        Location location3 = new Location(3, "Dummy Location #3", 23,
                90, "123 Beach Drive", "Kansas City", "Kansas",
                311239, LocationType.WAREHOUSE, 12341231,
                "www.ugh.com");

        Location location4 = null;

        assertEquals(location1,location1);
        assertNotEquals(location1, location2);
        assertNotEquals(location1, location4);
        assertEquals(location1, location3);


    }
}

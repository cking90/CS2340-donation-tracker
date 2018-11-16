//Made by Daniel
package edu.gatech.cs2340.nonprofitdonationtracker;

import org.junit.Test;

import edu.gatech.cs2340.nonprofitdonationtracker.models.Category;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Location;
import edu.gatech.cs2340.nonprofitdonationtracker.models.LocationType;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Donation;

import static org.junit.Assert.assertEquals;


/**
 * Made by Daniel
 * Tests the addDonationToLocation method in Location.java
 */
public class AddDonationToLocationTest {

    @Test
    public void addDonationToLocationTest() {
        //test that the for loop will not run if Location is empty
        assertEquals(Location.getLocationList().size(), 0);

        //make new location to add to locationList
        Location location1 = new Location(3, "Dummy Location", 12,
                30, "5252 Baker Street", "Evans", "GA",
                30809, LocationType.DROPOFF, 706750933,
                "www.why.com");
        Location location2 = new Location(2, "Dummy Location #2", 52,
                60, "1294 Haulding Str", "PinkerTown", "GA",
                31119, LocationType.STORE, 12341231,
                "www.cause.com");

        Location location3 = new Location(1, "Dummy Location #3", 23,
                90, "123 Beach Drive", "Kansas City", "Kansas",
                311239, LocationType.WAREHOUSE, 12341231,
                "www.ugh.com");
        Location.addLocationLocal(location1);
        Location.addLocationLocal(location2);
        //Location.addLocationLocal(location3);

        Donation d1 = new Donation("item1", "a hat",
                "a cool hat", 56.25f, Category.HAT);
        Donation d2 = new Donation("item2", "a clothe",
                "a large clothe", 8.25f, Category.CLOTHING);

        //test a successful add
        Location.addDonationToLocation(3, d1);
        assertEquals(location1.getDonations().get(0), d1);

        //test adding to a donation with improper key
        Location.addDonationToLocation(1, d2);
        assertEquals(location3.getDonations().size(), 0);
    }
}

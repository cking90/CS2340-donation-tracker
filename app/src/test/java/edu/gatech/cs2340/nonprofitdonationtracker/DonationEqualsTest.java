package edu.gatech.cs2340.nonprofitdonationtracker;



import org.junit.Test;

import edu.gatech.cs2340.nonprofitdonationtracker.models.Category;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Donation;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Location;
import edu.gatech.cs2340.nonprofitdonationtracker.models.LocationType;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Daniel
 * test to check if two equal locations are recognized as equal
 */
public class DonationEqualsTest {

    /**
     * test to check if two equal locations are recognized as equal
     */
    @Test
    public void locationEqualsTest() {

        Donation d1 = new Donation("item1", "a hat",
                "a cool hat", 56.25f, Category.HAT);
        Donation d2 = new Donation("item2", "a clothe",
                "a large clothe", 8.25f, Category.CLOTHING);
        Donation d3 = new Donation("item1", "something",
                "more", 8.26f, Category.HAT);
        Donation d4 = null;

        Location location1 = new Location(3, "Dummy Location", 12,
                30, "5252 Baker Street", "Evans", "GA",
                30809, LocationType.DROPOFF, 706750933,
                "www.why.com");


        assertEquals(d1, d3);
        assertNotEquals(d1, d2);
        assertNotEquals(d1, null);
        assertEquals(d1, d1);
        assertFalse(d1.equals(location1));
    }
}

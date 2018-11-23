package edu.gatech.cs2340.nonprofitdonationtracker.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DonationInfo {
    private static final List<Donation> allDonations = new ArrayList<>();

    public static void addDonationToAllDonations(Donation d) {
        allDonations.add(d);
    }
    public static float getNumDonationsInCategory(Category category) {
        float count = 0;
        for (Donation d: allDonations) {
            if (d.getCategory().equals(category)) {
                count++;
            }
        }
        return count;
    }
}

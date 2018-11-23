package edu.gatech.cs2340.nonprofitdonationtracker.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static float[] getValueOfInventoryPerMonth() {
        float[] valuesPerMonth = new float[12];
        for (Donation d: allDonations) {
            int month = getMonth(d.getDate());
            valuesPerMonth[month] += d.getValue();
        }
        return valuesPerMonth;
    }

    public static float[] getIncomePerMonth() {
        float[] incomesPerMonth = new float[12];
        Set<Location> locations = Location.getLocationList();
        for (Location location : locations) {
            Map<Category, Float> markups = location.getPercentMarkups();
            if (location.getType().equals(LocationType.STORE)) {
                for (Donation d: location.getDonations()) {
                    int month = getMonth(d.getDate());
                    float markup = markups.get(d.getCategory());
                    incomesPerMonth[month] += (d.getValue() * markup);
                }
            }
        }
        return incomesPerMonth;
    }

    public static HashMap<Location, Float> getDonationsPerMonthPerLocation() {
        HashMap<Location, Float> donationRatePerLocation = new HashMap<>();
        for (Location location : Location.getLocationList()) {
            float rate = location.donationsPerMonthRate();
            donationRatePerLocation.put(location, rate);
        }
        return donationRatePerLocation;
    }

    /**
     * Helper method that returns the month for the particular date
     * @param date
     * @return month
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }


}

package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import java.util.Date;

public class Donation {
    private String name;
    private Date timeStamp;
    private String shortDescription;
    private String longDescription;
    private float value;
    private Category category;

    public Donation(String name, String shortDescription,
                        String longDescription, float value, Category category) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.timeStamp = new Date();
    }

    public Date getDate() {
        return this.timeStamp;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public float getValue() {
        return this.value;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }



}

enum Category{
    CLOTHING, HAT, KITCHEN, ELECTRONICS, HOUSEHOLD, OTHER;
}

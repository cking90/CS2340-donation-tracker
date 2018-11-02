package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import java.util.Date;

public class Donation {
    private final String name;
    private final Date timeStamp;
    private final String shortDescription;
    private final String longDescription;
    private final float value;
    private final Category category;

    public Donation(String name, String shortDescription,
                        String longDescription, float value, Category category) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.timeStamp = new Date();
    }

    public Donation(String name, String shortDescription,
                    String longDescription, float value, Category category, Date date) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.timeStamp = date;
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

//    public void setDate(Date timeStamp) {this.timeStamp = timeStamp;}
//
//    public void setShortDescription(String shortDescription) {
//        this.shortDescription = shortDescription;
//    }
//
//    public void setLongDescription(String longDescription) {
//        this.longDescription = longDescription;
//    }
//
//    public void setValue(float value) {this.value = value;}
//
//    public void setCategory(Category category) {this.category = category;}
//
//    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return this.name;
    }
}

enum Category{
    CLOTHING, HAT, KITCHEN, ELECTRONICS, HOUSEHOLD, OTHER
}

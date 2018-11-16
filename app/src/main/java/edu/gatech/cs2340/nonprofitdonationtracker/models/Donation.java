package edu.gatech.cs2340.nonprofitdonationtracker.models;

import java.util.Date;

/**
 * Represents an item donated to the organization
 * at a certain location. All donations are tied
 * to a specific location through a reference in the
 * Location class
 */
public class Donation {
    private final String name;
    private final Date timeStamp;
    private final String shortDescription;
    private final String longDescription;
    private final float value;
    private final Category category;

    /**
     * Constructor for the donation object. Date of object
     * assigned to the current time
     *
     * @param name of the donation
     * @param shortDescription short message describing the
     *                         donation
     * @param longDescription a long message describing the
     *                        donation in more detail
     * @param value price of the donation
     * @param category the type of donation
     */
    public Donation(String name, String shortDescription,
                        String longDescription, float value, Category category) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.timeStamp = new Date();
    }

    /**
     * Constructor for the donation object that sets
     * the date instance variable to the passed in date
     *
     * @param name of the donation
     * @param shortDescription short message describing the
     *                         donation
     * @param longDescription a long message describing the
     *                        donation in more detail
     * @param value price of the donation
     * @param category the type of donation
     * @param date the date the donation was made
     */
    public Donation(String name, String shortDescription,
                    String longDescription, float value, Category category, Date date) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.timeStamp = (Date) date.clone();
    }

    /**
     *
     * @return Date instance variable
     */
    public Date getDate() {
        return (Date) this.timeStamp.clone();
    }

    /**
     *
     * @return short description instance variable
     * of the donation
     */
    public String getShortDescription() {
        return this.shortDescription;
    }

    /**
     *
     * @return long description instance variable of
     * the object
     */
    public String getLongDescription() {
        return this.longDescription;
    }

    /**
     *
     * @return price of the donation
     */
    public float getValue() {
        return this.value;
    }

    /**
     *
     * @return category of the variable
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     *
     * @return name of the donation
     */
    public String getName() {
        return this.name;
    }

    /**
     * tests equality of Donations by name
     *
     * @param d1 first donation
     * @return boolean value if they are equal or not
     */
    public boolean equals(Object d1) {
        if (this == d1) {
            return true;
        }
        if (!(d1 instanceof Donation)) {
            return false;
        }
        Donation other = (Donation) d1;
        return other.name.equals(this.name);
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
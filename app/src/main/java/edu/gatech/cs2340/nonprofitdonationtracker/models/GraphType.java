package edu.gatech.cs2340.nonprofitdonationtracker.models;

public enum GraphType {
    ITEM_BY_CATEGORY("Number of items per Category"),
    INVENTORY_VALUE_BY_MONTH("Value of Inventory by month");

    private String friendlyName;

    GraphType(String graphTypeInString) {
        this.friendlyName = graphTypeInString;
    }

    @Override
    public String toString() {
        return friendlyName;
    }
}

package com.tek.landlord.domain;

import android.provider.BaseColumns;

public class Unit {

    private final int id;
    private final int propertyId;
    private final String name;
    private final boolean isRented;
    private double rentAmount;

    public Unit(int id, int propertyId, String name, boolean isRented) {
        super();
        this.id = id;
        this.propertyId = propertyId;
        this.name = name;
        this.isRented = isRented;
    }

    public static abstract class UnitEntry implements BaseColumns {
        public static final String TABLE_NAME = "unit";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IS_RENTED = "is_rented";
        public static final String COLUMN_PROPERTY_ID = "property_id";
    }

    public int getId() {
        return this.id;
    }

    public int getPropertyId() {
        return this.propertyId;
    }

    public String getName() {
        return this.name;
    }

    public boolean isRented() {
        return this.isRented;
    }

}

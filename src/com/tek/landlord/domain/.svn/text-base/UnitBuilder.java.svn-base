package com.tek.landlord.domain;


public class UnitBuilder {

    private int id;
    private int propertyId;
    private String name;
    private boolean isRented = false;

    private UnitBuilder() {}
		
    public static final UnitBuilder aUnit() {
    	return new UnitBuilder();
    }
    
    public UnitBuilder withId(final int id) {
    	this.id = id;
    	return this;
    }
    
    public UnitBuilder withPropertyId(final int propertyId) {
    	this.propertyId = propertyId;
    	return this;
    }
    
    public UnitBuilder withName(final String name) {
    	this.name = name;
    	return this;
    }
    
    public UnitBuilder isRented() {
    	this.isRented = true;
    	return this;
    }
    
    public Unit build() {
    	return new Unit(this.id, this.propertyId, this.name, this.isRented);
    }
}

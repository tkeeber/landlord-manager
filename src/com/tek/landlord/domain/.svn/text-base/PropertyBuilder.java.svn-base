package com.tek.landlord.domain;

public class PropertyBuilder {

    private String name;
    private Integer type;
    private String address;
    private String city;
    private String state;
    private String postcode;
    private String country;
    private long id;
    private long parentPropertyId;
    private boolean isRented = false;
    private double rentAmount;
    private String notes;
    
    public static final PropertyBuilder aProperty() {
        return new PropertyBuilder();
    }

    private PropertyBuilder() {

    }
    
    public PropertyBuilder withParentPropertyId(final long parentPropertyId) {
    	this.parentPropertyId = parentPropertyId;
    	return this;
    }
    
    public PropertyBuilder withRentAmount(final double rentAmount) {
    	this.rentAmount = rentAmount;
    	return this;
    }
    
    public PropertyBuilder isRented() {
    	this.isRented = true;
    	return this;
    }

    public PropertyBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public PropertyBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PropertyBuilder withType(Integer type) {
        this.type = type;
        return this;
    }

    public PropertyBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public PropertyBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public PropertyBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public PropertyBuilder withPostCode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public PropertyBuilder withCountry(String country) {
        this.country = country;
        return this;
    }
    
    public PropertyBuilder withNotes(String notes) {
    	this.notes = notes;
    	return this;
    }

    public Property build() {
        return new Property(this.id, this.name, this.type, this.address, this.city, this.state, this.postcode, this.country, this.parentPropertyId, this.isRented, this.rentAmount, this.notes);
    }
}

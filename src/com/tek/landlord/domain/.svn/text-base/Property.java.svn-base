package com.tek.landlord.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

public class Property implements Parcelable {

    private final long id;
    private final String name;
    private final Integer type;
    private final String address;
    private final String city;
    private final String state;
    private final String postcode;
    private final String country;
    private final boolean isRented;
    private final double rentAmount;
	private final String notes; 

	//TODO makes property cross mutable
    private long parentPropertyId;

    
    public static abstract class PropertyEntry implements BaseColumns {
        public static final String TABLE_NAME = "property";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_STATE = "state";
        public static final String COLUMN_POSTCODE = "postcode";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_PARENT_PROPERTY_ID = "parent_property_id";
        public static final String COLUMN_IS_RENTED = "is_rented";
        public static final String COLUMN_RENT_AMOUNT = "rent_amount";
		public static final String COLUMN_NOTES = "notes";
    }

    public Property(long id, String name, Integer type, String address, String city, String state, String postcode, String country, final long parentPropertyId,
            final boolean isRented, final double rentAmount, final String notes) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
        this.isRented = isRented;
        this.rentAmount = rentAmount;
        this.parentPropertyId = parentPropertyId;
		this.notes = notes;
    }

    public void setParentId(long id) {
    	this.parentPropertyId = id;
    }
    
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getType() {
        return this.type;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public String getCountry() {
        return this.country;
    }

    public long getParentPropertyId() {
        return this.parentPropertyId;
    }

    public boolean isRented() {
        return this.isRented;
    }

    public double getRentAmount() {
        return this.rentAmount;
    }
    
    public String getNotes() {
    	return notes;
    }

    public static Parcelable.Creator<Property> getCreator() {
        return CREATOR;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(this.id);
        out.writeString(this.name);
        out.writeString(this.address);
        out.writeInt(this.type);
        out.writeString(this.city);
        out.writeString(this.country);
        out.writeString(this.postcode);
        out.writeString(this.state);
        out.writeInt(this.isRented ? 1 : 0);
        out.writeDouble(this.rentAmount);
        out.writeLong(this.parentPropertyId);
        out.writeString(this.notes);

    }

    public static final Parcelable.Creator<Property> CREATOR = new Parcelable.Creator<Property>() {
        public Property createFromParcel(Parcel in) {
            return PropertyBuilder.aProperty()
                .withId(in.readInt())
                .withName(in.readString())
                .withAddress(in.readString())
                .withType(in.readInt())
                .withCity(in.readString())
                .withCountry(in.readString())
                .withPostCode(in.readString())
                .withState(in.readString())
                .withNotes(in.readString())
                .build();
        }

        public Property[] newArray(int size) {
            return new Property[size];
        }
    };

}

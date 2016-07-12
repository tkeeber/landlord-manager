package com.tek.landlord.wizard;

import java.util.ArrayList;
import java.util.List;

import com.tek.landlord.domain.Property;
import com.tek.landlord.domain.Unit;

public class PersistablePropertyBuilder {

    private Property property;
    private List<Property> units;

    public static final PersistablePropertyBuilder aPersistableProperty() {
        return new PersistablePropertyBuilder();
    }

    private PersistablePropertyBuilder() {
        super();
        units = new ArrayList<Property>();
    }

    public boolean isPropertySet() {
    	return this.property != null;
    }
    
    public PersistablePropertyBuilder withUnits(List<Property> units) {
        this.units = units;
        return this;
    }

    public PersistablePropertyBuilder withProperty(Property property) {
        this.property = property;
        return this;
    }

    public PersistableProperty build() {
        return new PersistableProperty(this.property, this.units);
    }

	public boolean isPropertyUnitSet() {
		return this.units.size() != 0;
	}
}

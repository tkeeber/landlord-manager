package com.tek.landlord.service;

import java.util.List;

import android.content.Context;

import com.tek.landlord.dao.NewsfeedDao;
import com.tek.landlord.dao.PropertyDao;
import com.tek.landlord.domain.NewsfeedItem;
import com.tek.landlord.domain.NewsfeedItemBuilder;
import com.tek.landlord.domain.Property;

public class PropertyService {

	private final PropertyDao propertyDao;
	private final NewsfeedDao newsfeedDao;

	public PropertyService(Context context) {
		propertyDao = new PropertyDao(context);
		newsfeedDao = new NewsfeedDao(context);
	}

	public void createProperty(Property property, List<Property> units) {
		propertyDao.open();
		long parentPropertyId = propertyDao.createProperty(property);
		propertyDao.close();

		saveUnitsFor(parentPropertyId, units);

		NewsfeedItem newsfeedItem = NewsfeedItemBuilder
				.aNewPropertyNewsfeedItem(property.getName(),
						property.getAddress()).build();
		newsfeedDao.open();
		newsfeedDao.createNewsfeedItem(newsfeedItem);
		newsfeedDao.close();
	}

	public void saveUnitsFor(long parentPropertyId, List<Property> units) {
		if (units == null) return;
		
		propertyDao.open();
		
		for (Property unit : units) {
			unit.setParentId(parentPropertyId);
			propertyDao.createProperty(unit);
		}
		propertyDao.close();

	}
}

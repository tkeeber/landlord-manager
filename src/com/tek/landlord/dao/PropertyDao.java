package com.tek.landlord.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.tek.landlord.database.LandlordDatabaseHelper;
import com.tek.landlord.domain.Property;
import com.tek.landlord.domain.Property.PropertyEntry;

public class PropertyDao {
    // Database fields
    private SQLiteDatabase database;
    private LandlordDatabaseHelper dbHelper;
    
    private static final String PROPERTY_COUNT_SQL = "select count(*) from " + PropertyEntry.TABLE_NAME;
    
    public static String[] allColumns = {
            PropertyEntry._ID, PropertyEntry.COLUMN_NAME, PropertyEntry.COLUMN_TYPE, PropertyEntry.COLUMN_ADDRESS, PropertyEntry.COLUMN_CITY,
            PropertyEntry.COLUMN_STATE, PropertyEntry.COLUMN_POSTCODE, PropertyEntry.COLUMN_IS_RENTED, PropertyEntry.COLUMN_PARENT_PROPERTY_ID,
            PropertyEntry.COLUMN_RENT_AMOUNT, PropertyEntry.COLUMN_NOTES
            
    };

    public PropertyDao(Context context) {
        this.dbHelper = new LandlordDatabaseHelper(context);
    }

    public void open() throws SQLException {
        this.database = this.dbHelper.getWritableDatabase();
    }

    public void close() {
        this.dbHelper.close();
    }

    public Cursor getProperties() {
        return this.database.query(PropertyEntry.TABLE_NAME, allColumns, null, null, null, null, null);
    }

    public int getPropertiesCount() {
    	Cursor mCount= database.rawQuery(PROPERTY_COUNT_SQL, null);
    	mCount.moveToFirst();
    	int count= mCount.getInt(0);
    	mCount.close();
        return count;
    }
    

    
    public long createProperty(Property propertyItem) {
        ContentValues values = new ContentValues();
        values.put(PropertyEntry.COLUMN_NAME, propertyItem.getName());
        values.put(PropertyEntry.COLUMN_ADDRESS, propertyItem.getAddress());
        values.put(PropertyEntry.COLUMN_TYPE, propertyItem.getType());
        values.put(PropertyEntry.COLUMN_STATE, propertyItem.getState());
        values.put(PropertyEntry.COLUMN_POSTCODE, propertyItem.getPostcode());
        values.put(PropertyEntry.COLUMN_COUNTRY, propertyItem.getCountry());
        values.put(PropertyEntry.COLUMN_IS_RENTED, propertyItem.isRented());
        values.put(PropertyEntry.COLUMN_RENT_AMOUNT, propertyItem.getRentAmount());
        values.put(PropertyEntry.COLUMN_PARENT_PROPERTY_ID, propertyItem.getParentPropertyId());
        values.put(PropertyEntry.COLUMN_NOTES, propertyItem.getNotes());
        return this.database.insert(PropertyEntry.TABLE_NAME, null, values);
        // Cursor cursor = this.database.query(LandlordDatabaseHelper.TABLE_COMMENTS, allColumns, LandlordDatabaseHelper.COLUMN_ID + " = " + insertId, null, null,
        // null, null);
        // cursor.moveToFirst();
        // Comment newComment = cursorToComment(cursor);
        // cursor.close();
        // return newComment;
    }
  
}

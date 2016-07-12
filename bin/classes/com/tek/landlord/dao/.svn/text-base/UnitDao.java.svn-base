package com.tek.landlord.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.tek.landlord.database.LandlordDatabaseHelper;
import com.tek.landlord.domain.Property;
import com.tek.landlord.domain.Unit;
import com.tek.landlord.domain.Unit.UnitEntry;

public class UnitDao {
    // Database fields
    private SQLiteDatabase database;
    private LandlordDatabaseHelper dbHelper;
    public static String[] allColumns = {
            UnitEntry._ID, UnitEntry.COLUMN_NAME, UnitEntry.COLUMN_PROPERTY_ID, UnitEntry.COLUMN_IS_RENTED
    };

    public UnitDao(Context context) {
        this.dbHelper = new LandlordDatabaseHelper(context);
    }

    public void open() throws SQLException {
        this.database = this.dbHelper.getWritableDatabase();
    }

    public void close() {
        this.dbHelper.close();
    }

    public Cursor getUnitsForProperty(int propertyId) {
        return this.database.query(UnitEntry.TABLE_NAME, allColumns, UnitEntry.COLUMN_PROPERTY_ID + " = " + propertyId, null, null, null, null);
    }

    public void createUnitForProporty(Property property, Unit unit) {
        ContentValues values = new ContentValues();
        values.put(UnitEntry.COLUMN_NAME, unit.getName());
        values.put(UnitEntry.COLUMN_PROPERTY_ID, property.getId());
        values.put(UnitEntry.COLUMN_IS_RENTED, unit.isRented());
        this.database.insert(UnitEntry.TABLE_NAME, null, values);
    }

}

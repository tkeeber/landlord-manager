package com.tek.landlord.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.tek.landlord.database.LandlordDatabaseHelper;
import com.tek.landlord.domain.Property;
import com.tek.landlord.domain.Tenant;
import com.tek.landlord.domain.Tenant.TenantEntry;
import com.tek.landlord.domain.Unit;

public class TenantDao {
    // Database fields
    private SQLiteDatabase database;
    private LandlordDatabaseHelper dbHelper;
    public static String[] allColumns = {
            TenantEntry._ID, TenantEntry.COLUMN_NAME, TenantEntry.COLUMN_EMAIL, TenantEntry.COLUMN_TELEPHONE
    };

    public TenantDao(Context context) {
        this.dbHelper = new LandlordDatabaseHelper(context);
    }

    public void open() throws SQLException {
        this.database = this.dbHelper.getWritableDatabase();
    }

    public void close() {
        this.dbHelper.close();
    }

    public Cursor getAllTenants() {
        return this.database.query(TenantEntry.TABLE_NAME, allColumns, null, null, null, null, null);
    }

    public void createTenantForUnit(Unit unit, Tenant tenant) {
        ContentValues values = new ContentValues();
        values.put(TenantEntry.COLUMN_NAME, tenant.getName());
        values.put(TenantEntry.COLUMN_TELEPHONE, tenant.getTelephone());
        long insertId = this.database.insert(TenantEntry.TABLE_NAME, null, values);
    }

}

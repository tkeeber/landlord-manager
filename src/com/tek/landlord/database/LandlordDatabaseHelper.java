package com.tek.landlord.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tek.landlord.domain.NewsfeedItem.NewsfeedEntry;
import com.tek.landlord.domain.Property.PropertyEntry;

public class LandlordDatabaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database
    // version.
    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "Landlord.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_NEWSFEED_TABLE = //
    "CREATE TABLE " + NewsfeedEntry.TABLE_NAME + " (" + NewsfeedEntry._ID + " INTEGER PRIMARY KEY," //
            + NewsfeedEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP //
            + NewsfeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP //
            + NewsfeedEntry.COLUMN_NAME_TYPE + INTEGER_TYPE + COMMA_SEP //
            + NewsfeedEntry.COLUMN_NAME_DESCRIPTION_TEXT + TEXT_TYPE + COMMA_SEP //
            + NewsfeedEntry.COLUMN_NAME_ACTIONED + INTEGER_TYPE + " )";
    
    private static final String SQL_CREATE_PROPERTY_TABLE = //
    		"CREATE TABLE " + PropertyEntry.TABLE_NAME + " (" + PropertyEntry._ID + " INTEGER PRIMARY KEY," //
    		+ PropertyEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP //
    		+ PropertyEntry.COLUMN_TYPE + INTEGER_TYPE + COMMA_SEP //
    		+ PropertyEntry.COLUMN_ADDRESS + TEXT_TYPE + COMMA_SEP //
    		+ PropertyEntry.COLUMN_CITY + TEXT_TYPE + COMMA_SEP //
    		+ PropertyEntry.COLUMN_STATE + TEXT_TYPE + COMMA_SEP //
    		+ PropertyEntry.COLUMN_COUNTRY + TEXT_TYPE + COMMA_SEP //
    		+ PropertyEntry.COLUMN_POSTCODE + TEXT_TYPE  + COMMA_SEP //
    		+ PropertyEntry.COLUMN_PARENT_PROPERTY_ID + INTEGER_TYPE  + COMMA_SEP //
    		+ PropertyEntry.COLUMN_RENT_AMOUNT + DOUBLE_TYPE  + COMMA_SEP //
    		+ PropertyEntry.COLUMN_NOTES + TEXT_TYPE  + COMMA_SEP //
    		+ PropertyEntry.COLUMN_IS_RENTED + INTEGER_TYPE + " )";

    private static final String SQL_INSERT_NEWSFEED_WELCOME = "INSERT INTO "
            + NewsfeedEntry.TABLE_NAME
            + " VALUES(1, 'today', 'Welcome', 0, 'Welcome to Landlord. This is the newsfeed page, all your property alerts and reminders will appear. To get start click here to set up your properties. ', 0);";

    private static final String DELETE_TABLE_NEWSFEED = "DROP TABLE IF EXISTS " + NewsfeedEntry.TABLE_NAME;
    private static final String DELETE_TABLE_PROPERTY = "DROP TABLE IF EXISTS " + PropertyEntry.TABLE_NAME;

    public LandlordDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_NEWSFEED_TABLE);
        db.execSQL(SQL_INSERT_NEWSFEED_WELCOME);
        db.execSQL(SQL_CREATE_PROPERTY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy
        // is
        // to simply to discard the data and start over
        db.execSQL(DELETE_TABLE_NEWSFEED);
        db.execSQL(DELETE_TABLE_PROPERTY);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}

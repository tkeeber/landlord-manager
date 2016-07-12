package com.tek.landlord.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.tek.landlord.database.LandlordDatabaseHelper;
import com.tek.landlord.domain.FinancialItem;
import com.tek.landlord.domain.FinancialItem.FinancialItemEntry;

public class FinancialDao  {

    // Database fields
    private SQLiteDatabase database;
    private LandlordDatabaseHelper dbHelper;
    public static String[] allColumns = {
    	FinancialItemEntry._ID, FinancialItemEntry.COLUMN_NAME_UNIT_ID, FinancialItemEntry.COLUMN_NAME_AMOUNT, 
    	FinancialItemEntry.COLUMN_NAME_CREATED_DATE, FinancialItemEntry.COLUMN_NAME_NOTES,
    	FinancialItemEntry.COLUMN_NAME_FREQUENCY, FinancialItemEntry.COLUMN_NAME_TYPE, FinancialItemEntry.COLUMN_NAME_IS_PAID
    };

    public FinancialDao(Context context) {
        this.dbHelper = new LandlordDatabaseHelper(context);
    }

    public void open() throws SQLException {
        this.database = this.dbHelper.getWritableDatabase();
    }

    public void close() {
        this.dbHelper.close();
    }

     public Cursor getAllExpenses(int propertyId) {        
         return this.database.query(FinancialItemEntry.TABLE_NAME, allColumns, null, null, null, null, null);
     }
     
     public Cursor getAllRevenueFor(int propertyId) {
         return this.database.query(FinancialItemEntry.TABLE_NAME, allColumns, null, null, null, null, null);
     }

     public void createRevenue() {
    	 
     }
     
     public void createExpense(FinancialItem financialItem) {
     ContentValues values = new ContentValues();
     values.put(FinancialItemEntry.COLUMN_NAME_UNIT_ID, financialItem.getUnitId());
     values.put(FinancialItemEntry.COLUMN_NAME_AMOUNT, financialItem.getAmount());
     values.put(FinancialItemEntry.COLUMN_NAME_TYPE, financialItem.getType());
     values.put(FinancialItemEntry.COLUMN_NAME_CREATED_DATE, financialItem.getDateCreated().getTime());
     values.put(FinancialItemEntry.COLUMN_NAME_FREQUENCY, financialItem.getFrequency());
     values.put(FinancialItemEntry.COLUMN_NAME_IS_PAID, financialItem.isExpensePaid());
     values.put(FinancialItemEntry.COLUMN_NAME_NOTES, financialItem.getNotes());

     long insertId = this.database.insert(FinancialItemEntry.TABLE_NAME, null, values);
//     Cursor cursor = this.database.query(LandlordDatabaseHelper.TABLE_COMMENTS, allColumns, LandlordDatabaseHelper.COLUMN_ID + " = " + insertId, null, null,
//     null, null);
//     cursor.moveToFirst();
//     Comment newComment = cursorToComment(cursor);
//     cursor.close();
//     return newComment;
     }
    //
    // private Comment cursorToComment(Cursor cursor) {
    // Comment comment = new Comment();
    // comment.setId(cursor.getLong(0));
    // comment.setComment(cursor.getString(1));
    // return comment;
    // }
}

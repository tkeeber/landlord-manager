package com.tek.landlord.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.tek.landlord.NewsfeedFragment;
import com.tek.landlord.database.LandlordDatabaseHelper;
import com.tek.landlord.domain.NewsfeedItem;
import com.tek.landlord.domain.NewsfeedItem.NewsfeedEntry;

public class NewsfeedDao {

	// Database fields
	private SQLiteDatabase database;
	private LandlordDatabaseHelper dbHelper;
	public static String[] allColumns = { NewsfeedEntry._ID,
			NewsfeedEntry.COLUMN_NAME_TITLE, NewsfeedEntry.COLUMN_NAME_DATE,
			NewsfeedEntry.COLUMN_NAME_TYPE,
			NewsfeedEntry.COLUMN_NAME_DESCRIPTION_TEXT,
			NewsfeedEntry.COLUMN_NAME_ACTIONED };

	public NewsfeedDao(Context context) {
		this.dbHelper = new LandlordDatabaseHelper(context);
	}

	public void open() throws SQLException {
		this.database = this.dbHelper.getWritableDatabase();
	}

	public void close() {
		this.dbHelper.close();
	}

	public Cursor getAllNonDismissedNewsfeedItems() {
		return this.database.query(NewsfeedEntry.TABLE_NAME, allColumns,
				NewsfeedEntry.COLUMN_NAME_ACTIONED + " != 1", null, null, null,
				null);
	}
	
	public void markItemAsRead(long id) {
		 ContentValues valuesToBeUpdated = new ContentValues();
		 valuesToBeUpdated.put(NewsfeedEntry.COLUMN_NAME_ACTIONED, 1);
		 this.database.update(NewsfeedEntry.TABLE_NAME, valuesToBeUpdated, NewsfeedEntry._ID + " = " + id, null);
	}

	public void createNewsfeedItem(NewsfeedItem newsfeedItem) {
		ContentValues values = new ContentValues();
		values.put(NewsfeedEntry.COLUMN_NAME_TYPE, newsfeedItem.getType());
		values.put(NewsfeedEntry.COLUMN_NAME_TITLE, newsfeedItem.getTitle());
		values.put(NewsfeedEntry.COLUMN_NAME_DATE, newsfeedItem.getDate());
		values.put(NewsfeedEntry.COLUMN_NAME_DESCRIPTION_TEXT,
				newsfeedItem.getDetail());
		values.put(NewsfeedEntry.COLUMN_NAME_ACTIONED, false);
		long insertId = this.database.insert(NewsfeedEntry.TABLE_NAME, null,
				values);
		// Cursor cursor =
		// this.database.query(LandlordDatabaseHelper.TABLE_COMMENTS,
		// allColumns, LandlordDatabaseHelper.COLUMN_ID + " = " + insertId,
		// null, null,
		// null, null);
		// cursor.moveToFirst();
		// Comment newComment = cursorToComment(cursor);
		// cursor.close();
		// return newComment;
	}
	//
	// private Comment cursorToComment(Cursor cursor) {
	// Comment comment = new Comment();
	// comment.setId(cursor.getLong(0));
	// comment.setComment(cursor.getString(1));
	// return comment;
	// }
}

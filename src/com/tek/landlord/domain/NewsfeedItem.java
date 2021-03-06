package com.tek.landlord.domain;

import android.provider.BaseColumns;

public class NewsfeedItem {

	private final long id;
    private String title;
    private String date;
    private String description;
    private Integer type;
    private Integer isActioned;
    
    /* Inner class that defines the table contents */
    public static abstract class NewsfeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "newsfeed_entry";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION_TEXT = "description";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_ACTIONED = "actioned";
    }


    public NewsfeedItem(final long id, String title, Integer type, String date, String detail) {
        super();
        this.title = title;
        this.type = type;
        this.date = date;
        this.description = detail;
        this.id = id;
    }

    public long getId() {
    	return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }

    public String getDate() {
        return this.date;
    }

    public String getDetail() {
        return this.description;
    }

    public Integer getType() {
        return this.type;
    }

}

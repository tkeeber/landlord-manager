package com.tek.landlord.domain;

import android.provider.BaseColumns;

public class Tenant {

	private final int id;
	private final int unitId;
	private final String name;
	private final String emails;
	private final String telephone;
	
    public Tenant(final int id,final  int unitId, String name, final String emails,final String telephone) {
		super();
		this.id = id;
		this.unitId = unitId;
		this.name = name;
		this.emails = emails;
		this.telephone = telephone;
	}

	public static abstract class TenantEntry implements BaseColumns {
    	  public static final String TABLE_NAME = "tenant";
          public static final String COLUMN_NAME = "name";
          public static final String COLUMN_EMAIL = "email";
          public static final String COLUMN_TELEPHONE = "telephone";
          
    }

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmails() {
		return emails;
	}

	public String getTelephone() {
		return telephone;
	}

	public int getUnitId() {
		return unitId;
	}

}

package com.tek.landlord.domain;

import java.util.Date;

import android.provider.BaseColumns;

public class FinancialItem {

	private final int unitId;
	private final int type;
	private final double amount;
	private final String notes;
	private final Date dateCreated;
	private final int frequency;
	private final boolean hasReminder;
	private final boolean expensePaid;
	
	 /* Inner class that defines the table contents */
    public static abstract class FinancialItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "financial_item";
        public static final String COLUMN_NAME_UNIT_ID = "unit_id";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_NOTES = "notes";
        public static final String COLUMN_NAME_FREQUENCY = "frequency";
        public static final String COLUMN_NAME_CREATED_DATE = "created_date";
        public static final String COLUMN_NAME_IS_PAID = "is_paid";
    }
    
	public FinancialItem(int unitId, int type, double amount, String notes,
			Date dateCreated, int frequency, boolean hasReminder,
			boolean expensePaid) {
		super();
		this.unitId = unitId;
		this.type = type;
		this.amount = amount;
		this.notes = notes;
		this.dateCreated = dateCreated;
		this.frequency = frequency;
		this.hasReminder = hasReminder;
		this.expensePaid = expensePaid;
	}

	public int getUnitId() {
		return unitId;
	}

	public int getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public String getNotes() {
		return notes;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public int getFrequency() {
		return frequency;
	}

	public boolean isHasReminder() {
		return hasReminder;
	}

	public boolean isExpensePaid() {
		return expensePaid;
	}
	
	
	
}

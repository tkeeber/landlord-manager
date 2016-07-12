package com.tek.landlord.domain;

import java.util.Date;

public class FinancialItemBuilder {

	private int unitId;
	private int type;
	private double amount;
	private String notes;
	private Date dateCreated;
	private int frequency;
	private boolean hasReminder;
	private boolean expensePaid;
	
	public static FinancialItemBuilder aFinancialItem() {
		return new FinancialItemBuilder();
	}
	private FinancialItemBuilder() {}

	public  FinancialItemBuilder withUnitId(int unitId) {
		this.unitId = unitId;
		return this;
	}

	public FinancialItemBuilder withType(int type) {
		this.type = type;
		return this;
	}

	public FinancialItemBuilder withAmount(double amount) {
		this.amount= amount;
		return this;
	}

	public  FinancialItemBuilder withNotes(String notes) {
		this.notes = notes;
		return this;
	}

	public FinancialItemBuilder withDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
		return this;
	}

	public FinancialItemBuilder withFrequency(int frequency) {
		this.frequency = frequency;
		return this;
	}

	public FinancialItemBuilder markHasReminder() {
		this.hasReminder = true;
		return this;
	}

	public FinancialItemBuilder markExpensePaid() {
		this.expensePaid = true;
		return this;
	}
	
	public FinancialItem build() {
		return new FinancialItem(frequency, frequency, amount, notes, dateCreated, frequency, expensePaid, this.hasReminder);
	}
	
	
}

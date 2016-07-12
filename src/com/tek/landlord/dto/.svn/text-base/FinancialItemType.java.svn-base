package com.tek.landlord.dto;

public enum FinancialItemType {
	MORTGAGE_REPAYMENT("Mortgage Repayments"),
    CONTENTS_INSURANCE("Contents Insurance"),
    HOUSE_INSURANCE("House Insurance"),
    OTHER("Other");

    private final String description;

    private FinancialItemType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
    	return this.description;
    }

	public static FinancialItemType fromDescription(String type) {
		for (FinancialItemType value : values()) {
			if (value.matchesDescription(type)) {
				return value;
			}
		}
		//TODO remove this before go live and fail gracefully.
		throw new RuntimeException("Could not find desciption type [" + type + "]");
	}
	
	public boolean matchesDescription(String descriptionToMatch) {
		return this.getDescription().equals(descriptionToMatch);
	}
}

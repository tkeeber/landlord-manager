package com.tek.landlord.dto;

public enum TimeIntervalDto {
	PER_MONTH("Per Month"),
	PER_WEEK("Per Week"),
	PER_DAY("Per Day"),
	QUARTELY("Quarterly"),
    ONE_OFF_PAYMENT("One off payment"),
    ANNUAL("Annual");

    private final String description;

    private TimeIntervalDto(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
    	return this.description;
    }
    
    public static TimeIntervalDto fromDescription(String type) {
		for (TimeIntervalDto value : values()) {
			if (value.matchesDescription(type)) {
				return value;
			}
		}
		throw new RuntimeException("Could not find desciption type [" + type + "]");
	}
    
	public boolean matchesDescription(String descriptionToMatch) {
		return this.getDescription().equals(descriptionToMatch);
	}
}

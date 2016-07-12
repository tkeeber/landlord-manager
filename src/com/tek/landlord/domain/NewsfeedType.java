package com.tek.landlord.domain;

public enum NewsfeedType {
    APPLICATION_MESSAGE(0, "Welcome"),
    NEW_PROPERTY_ADDED(1, "New Property Added"),
    RENT_DUE(2, "Rent Due");
    
    public static final int APPLICATION_MESSAGE_ID = 0;
    public static final int NEW_PROPERTY_ADDED_ID = 1;
    public static final int RENT_DUE_ID = 2;

    private final int identifer;
    private final String newsfeedPrefix;

    private NewsfeedType(final int identifier, final String newsfeedPrefix) {
        this.identifer = identifier;
        this.newsfeedPrefix = newsfeedPrefix;
    }

    public int getIdentifier() {
        return this.identifer;
    }

    public static NewsfeedType from(Integer newsfeedItemType) {
        switch (newsfeedItemType) {
        case 0:
            return NewsfeedType.APPLICATION_MESSAGE;
        case 1:
            return NewsfeedType.NEW_PROPERTY_ADDED;
        case 2:
            return NewsfeedType.RENT_DUE;
        }
        return null;
    }

    public String getTitlePrefix() {
        return this.newsfeedPrefix;
    }
}

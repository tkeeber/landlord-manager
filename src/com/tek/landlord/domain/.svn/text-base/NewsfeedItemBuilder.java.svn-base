package com.tek.landlord.domain;


public class NewsfeedItemBuilder {

	private long id;
    private String title;
    private String date;
    private String description;
    private Integer type;
    private Integer isActioned;

    private NewsfeedItemBuilder() {
        super();
    }

    public static final NewsfeedItemBuilder aNewPropertyNewsfeedItem(final String propertyName, final String propertyAddress) {
        return new NewsfeedItemBuilder().withType(NewsfeedType.NEW_PROPERTY_ADDED.getIdentifier())
            .withDescription(propertyAddress)
            .withTitle("Property " + propertyName + " added");
        // .withDate(DateUtils.today());
    }

    public static final NewsfeedItemBuilder aNewsfeedItem() {
        return new NewsfeedItemBuilder();
    }
    
    public NewsfeedItemBuilder withId(final long id ) {
    	this.id = id;
    	return this;
    }

    public NewsfeedItemBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public NewsfeedItemBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    public NewsfeedItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public NewsfeedItemBuilder withType(Integer type) {
        this.type = type;
        return this;
    }

    public NewsfeedItemBuilder markAsActioned() {
        this.isActioned = 1;
        return this;
    }

    public NewsfeedItem build() {
        return new NewsfeedItem(this.id, this.title, this.type, this.date, this.description);
    }
}

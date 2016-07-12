package com.tek.landlord.adapter;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.tek.landlord.R;
import com.tek.landlord.domain.NewsfeedType;

public class NewsfeedAdaptor {

    private final Drawable rentDueIcon;
    private final Drawable welcomeIcon;
    private final Drawable newPropertyIcon;

    private final Map<Integer, Drawable> newsFeedTypeToIcon = new HashMap<Integer, Drawable>();

    public NewsfeedAdaptor(Context context) {
        this.rentDueIcon = context.getResources().getDrawable(R.drawable.currency_pound_small);
        this.welcomeIcon = context.getResources().getDrawable(R.drawable.ic_launcher);
        this.newPropertyIcon = context.getResources().getDrawable(R.drawable.ic_launcher);

        this.newsFeedTypeToIcon.put(NewsfeedType.APPLICATION_MESSAGE_ID, this.welcomeIcon);
        this.newsFeedTypeToIcon.put(NewsfeedType.NEW_PROPERTY_ADDED_ID, this.newPropertyIcon);
        this.newsFeedTypeToIcon.put(NewsfeedType.RENT_DUE_ID, this.rentDueIcon);
    }

    public static NewsfeedType adapt(Integer newsfeedItemType) {
        return NewsfeedType.from(newsfeedItemType);
    }

    public Drawable adaptToIcon(Integer newsfeedItemType) {
        return this.newsFeedTypeToIcon.get(newsfeedItemType);
    }

    public static String buildTitle(NewsfeedType newsfeedType) {
        return new StringBuilder().append(newsfeedType.getTitlePrefix()).toString();
    }
}

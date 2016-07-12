package com.tek.landlord;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.tek.landlord.domain.NewsfeedItem;
import com.tek.landlord.domain.NewsfeedType;

public class NewsfeedItemFactory {

    private final Drawable rentDueIcon;
    private final Drawable welcomeIcon;

    public NewsfeedItemFactory(Context context) {
        this.rentDueIcon = context.getResources().getDrawable(R.drawable.currency_pound_small);
        this.welcomeIcon = context.getResources().getDrawable(R.drawable.ic_launcher);
    }

//    public final NewsfeedItem aWelcomeMessageNewsfeedItem() {
//        return new NewsfeedItem(this.welcomeIcon, "Welcome to Landlord", NewsfeedType.APPLICATION_MESSAGE, "",
//                "This is the landlord newsfeed. All you current events in one place. All app update information will also appear here.");
//
//    }
//
//    public final NewsfeedItem aRentDueNewsfeedItem(final String rentDueTitle, final String itemDate, final String detail) {
//        return new NewsfeedItem(this.rentDueIcon, rentDueTitle, NewsfeedType.RENT_DUE, itemDate, detail);
//    }
}

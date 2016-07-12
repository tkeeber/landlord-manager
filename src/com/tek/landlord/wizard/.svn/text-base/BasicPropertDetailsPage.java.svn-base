package com.tek.landlord.wizard;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.tek.landlord.domain.PropertyType;
import com.tek.landlord.wizard.framework.ModelCallbacks;
import com.tek.landlord.wizard.framework.Page;
import com.tek.landlord.wizard.framework.ReviewItem;

public class BasicPropertDetailsPage extends Page {

    public static final String PROPERTY_BASIC_DETAILS = "PROPERTY_BASIC_DETAILS";

    public static final String MULTI_BASIC_DETAILS_BUNDLE_KEY = PropertyType.MULITPLE_UNIT_HOUSE.getDesciption() + ":Property Details";
    public static final String SINGLE_BASIC_DETAILS_BUNDLE_KEY = PropertyType.SINGLE_TENANTED_HOUSE.getDesciption() + ":Property Details";

    public static final String NAME_DATA_KEY = "name";
    public static final String ADDRESS_DATA_KEY = "address";
    public static final String CITY_DATA_KEY = "city";
    public static final String STATE_DATA_KEY = "state";
    public static final String COUNTRY_DATA_KEY = "country";
    public static final String POSTCODE_DATA_KEY = "postcode";

    public BasicPropertDetailsPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    // public String getKey() {
    // return BASIC_DETAILS_BUNDLE_KEY;
    // }
    @Override
    public String getKey() {
        return (this.mParentKey != null) ? this.mParentKey + ":" + this.mTitle : this.mTitle;
    }

    @Override
    public Fragment createFragment() {
        return BasicPropertyDetailsFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("name", this.mData.getString(NAME_DATA_KEY), getKey(), -1));
        dest.add(new ReviewItem("address", this.mData.getString(ADDRESS_DATA_KEY), getKey(), -1));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(this.mData.getString(NAME_DATA_KEY));
    }
}
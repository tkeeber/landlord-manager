package com.tek.landlord.wizard;

import java.util.ArrayList;

import android.support.v4.app.Fragment;

import com.tek.landlord.wizard.framework.ModelCallbacks;
import com.tek.landlord.wizard.framework.Page;
import com.tek.landlord.wizard.framework.ReviewItem;

public class AddPropertyUnitPage extends Page {
	
	public static final String UNIT_BUNDLE_KEY = "unit-bundle-key";
	
    public static final String NUMBER_OF_UNITS = "number-of-units";
	public static final String UNITS = "units";

    public AddPropertyUnitPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }
    
    public String getKey() {
    	return UNIT_BUNDLE_KEY;
    }

    @Override
    public Fragment createFragment() {
        return AddPropertyUnitFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("Number of units", "" + this.mData.getInt(NUMBER_OF_UNITS), getKey(), 0));
    }

    @Override
    public boolean isCompleted() {
        return true;// this.mData.getInt(NUMBER_OF_UNITS) > 0;
    }
}
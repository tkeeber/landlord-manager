/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tek.landlord.wizard.framework;

import java.util.ArrayList;
import java.util.Arrays;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

/**
 * A page offering the user a number of mutually exclusive choices.
 */
public class PropertyTypeChoicePage extends Page {
    protected ArrayList<String> mChoices = new ArrayList<String>();

    public static final String PROPERTY_TYPE_PAGE_TITLE = "Property type";
    public static final String PROPERTY_TYPE_KEY = "property-type";

    public PropertyTypeChoicePage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public String getKey() {
        return (this.mParentKey != null) ? this.mParentKey + ":" + this.mTitle : this.mTitle;
    }

    @Override
    public Fragment createFragment() {
        return PropertyTypeChoiceFragment.create(getKey());
    }

    public String getOptionAt(int position) {
        return this.mChoices.get(position);
    }

    public int getOptionCount() {
        return this.mChoices.size();
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem(getTitle(), this.mData.getString(PROPERTY_TYPE_KEY), getKey()));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(this.mData.getString(PROPERTY_TYPE_KEY));
    }

    public PropertyTypeChoicePage setChoices(String... choices) {
        this.mChoices.addAll(Arrays.asList(choices));
        return this;
    }

    public PropertyTypeChoicePage setValue(String value) {
        this.mData.putString(PROPERTY_TYPE_KEY, value);
        return this;
    }
}

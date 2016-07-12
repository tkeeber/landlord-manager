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

package com.tek.landlord.wizard;

import android.content.Context;

import com.tek.landlord.domain.PropertyType;
import com.tek.landlord.wizard.framework.BranchPage;
import com.tek.landlord.wizard.framework.PageList;

public class NewPropertyWizardModel extends com.tek.landlord.wizard.framework.AbstractWizardModel {

    public NewPropertyWizardModel(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {
        return new PageList(new BranchPage(this, "Property type")
        //
        .addBranch(PropertyType.SINGLE_TENANTED_HOUSE.getDesciption(), new BasicPropertDetailsPage(this, "Property Details"))
            .addBranch(PropertyType.MULITPLE_UNIT_HOUSE.getDesciption(), new BasicPropertDetailsPage(this, "Property Details"),
                    new AddPropertyUnitPage(this, "Units/Flats").setRequired(true)));
    }

    // public Property getPropertyToSave() {
    // PropertyBuilder propertyBuilder = PropertyBuilder.aProperty();
    // for (Page page : getCurrentPageSequence()) {
    // propertyBuilder = populatePropertyDetails(page);
    // if (propertyBuilder != null) {
    // return propertyBuilder.build();
    // }
    // }
    // return null;
    // }
    //
    // private PropertyBuilder populatePropertyDetails(Page page) {
    // if (page.getKey().contains("Property Details")) {
    // Bundle propertyBundle = page.getData();
    // PropertyBuilder property = PropertyBuilder.aProperty()
    // .withName(propertyBundle.getString(BasicPropertDetailsPage.NAME_DATA_KEY))
    // .withAddress(BasicPropertDetailsPage.ADDRESS_DATA_KEY)
    // ;
    // return property;
    // }
    // return null;
    // }
    //
    // private List<Property> populatePropertyUnits(Page page) {
    // if (page.getKey().contains("Units")) {
    // Bundle pageBundle = page.getData();
    // return pageBundle.getParcelableArrayList(AddPropertyUnitPage.UNITS);
    // }
    // return null;
    // }
    //
    // public List<Property> getUnitsToSave(long propertyId) {
    // return null;
    // }
}

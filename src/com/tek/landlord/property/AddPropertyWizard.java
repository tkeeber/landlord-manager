package com.tek.landlord.property;

import org.codepond.wizardroid.WizardFlow;
import org.codepond.wizardroid.layouts.BasicWizardLayout;

public class AddPropertyWizard extends BasicWizardLayout {

    @Override
    public WizardFlow onSetup() {
        return new WizardFlow.Builder().addStep(AddPropertyBasicDetails.class, true).create();
        // .addStep(AddPropertySingleUnitTenantInfoDetails.class)
        // .addStep(AddPropertyTenantSingleUnitDetails.class)
        // .addStep(AddPropertyOutgoingFinancialDetails.class).create();
    }

}

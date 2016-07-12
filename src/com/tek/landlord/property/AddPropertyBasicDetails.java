package com.tek.landlord.property;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.tek.landlord.R;
import com.tek.landlord.dao.NewsfeedDao;
import com.tek.landlord.dao.PropertyDao;
import com.tek.landlord.domain.NewsfeedItem;
import com.tek.landlord.domain.NewsfeedItemBuilder;
import com.tek.landlord.domain.Property;
import com.tek.landlord.domain.PropertyBuilder;

public class AddPropertyBasicDetails extends WizardStep implements OnClickListener {

    @ContextVariable
    private String propertyName;
    @ContextVariable
    private String properyAddress;
    @ContextVariable
    private String propertyCity;
    @ContextVariable
    private String propertyState;
    @ContextVariable
    private String propertyPostcode;
    @ContextVariable
    private String propertyCountry;
    @ContextVariable
    private String propertyType;

    private EditText mPropertyName;
    private EditText mPropertyAddress;
    private EditText mPropertyCity;
    private EditText mPropertyState;
    private EditText mPropertyPostcode;
    private Spinner mPropertyCountry;

    private Button mNextButton;
    private Button mPreviousButton;

    // Set your layout here
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_property_basic, container, false);

        View wizardLayout = inflater.inflate(R.layout.wizard, container, false);
        this.mNextButton = (Button) wizardLayout.findViewById(R.id.wizard_next_button);
        this.mNextButton.setOnClickListener(this);
        this.mPreviousButton = (Button) wizardLayout.findViewById(R.id.wizard_previous_button);

        this.mPropertyName = (EditText) v.findViewById(R.id.enter_property_name);

        this.mPropertyAddress = (EditText) v.findViewById(R.id.enter_property_address);
        this.mPropertyCity = (EditText) v.findViewById(R.id.enter_property_city);
        this.mPropertyState = (EditText) v.findViewById(R.id.enter_property_state_county);
        this.mPropertyPostcode = (EditText) v.findViewById(R.id.enter_property_postcode);
        this.mPropertyCountry = (Spinner) v.findViewById(R.id.enter_property_country);

        this.mPropertyName.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                validateWizardStep();
            }
        });
        return v;
    }

    private void validateWizardStep() {
        if (!isEmpty(AddPropertyBasicDetails.this.mPropertyName)) {
            AddPropertyBasicDetails.this.notifyCompleted(true);
        } else {
            AddPropertyBasicDetails.this.notifyCompleted(false);
        }
    }

    @Override
    public void onExit(int exitCode) {
        switch (exitCode) {
        case WizardStep.EXIT_NEXT:
            bindDataFields();
            break;
        case WizardStep.EXIT_PREVIOUS:
            // Do nothing...
            break;
        }
    }

    private void bindDataFields() {
        // this.propertyName = this.mPropertyName.getText().toString();
        // this.properyAddress = this.mPropertyAddress.getText().toString();
        // this.propertyCity = this.mPropertyCity.getText().toString();
        // this.propertyState = this.mPropertyState.getText().toString();
        // this.propertyPostcode = this.mPropertyPostcode.getText().toString();
        // this.propertyCountry = this.mPropertyCountry.getSelectedItem().toString();
        // this.propertyType = this.mPropertyType.getSelectedItem().toString();
        Property property = PropertyBuilder.aProperty()
            .withName(this.mPropertyName.getText().toString())
            .withAddress(this.mPropertyAddress.getText().toString())
            .withCity(this.mPropertyCity.getText().toString())
            .withState(this.mPropertyState.getText().toString())
            .withPostCode(this.mPropertyPostcode.getText().toString())
            .withCountry(this.mPropertyCountry.getSelectedItem().toString())
            .build();
        saveProperty(property);
    }

    private void saveProperty(final Property property) {

        PropertyDao propertyDao = new PropertyDao(this.getActivity());
        propertyDao.open();
        propertyDao.createProperty(property);
        propertyDao.close();
        NewsfeedItem item = NewsfeedItemBuilder.aNewPropertyNewsfeedItem(property.getName(), property.getAddress()).build();
        NewsfeedDao newsfeedDao = new NewsfeedDao(this.getActivity());
        newsfeedDao.open();
        newsfeedDao.createNewsfeedItem(item);
        newsfeedDao.close();
        // SavePropertyTask savePropertyTask = new SavePropertyTask(this.getActivity(), new CloseActivityHandler() {
        //
        // @Override
        // public void close() {
        // }
        // });
        // savePropertyTask.execute(property);
    }

    public void onClick(View v) {
        if (!isEmpty(this.mPropertyName)) {
            // carry on

        } else {

        }
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

}

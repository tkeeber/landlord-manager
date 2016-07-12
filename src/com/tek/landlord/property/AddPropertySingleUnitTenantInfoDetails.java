package com.tek.landlord.property;

import java.util.Calendar;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.tek.landlord.R;

public class AddPropertySingleUnitTenantInfoDetails extends WizardStep {
    private EditText mStartTenancyButton;
    private EditText mEndTenancyButton;

    @ContextVariable
    private Calendar tenancyStartDate;

    @ContextVariable
    private Calendar tenancyEndDate;

    // Set your layout here
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_property_single_unit_tenant_info_view, container, false);
        View wizardLayout = inflater.inflate(R.layout.wizard, container, false);

        initDates();

        Button nextButton = (Button) wizardLayout.findViewById(R.id.wizard_next_button);
        nextButton.setText("Not Currently Tenanted");
        this.mStartTenancyButton = (EditText) v.findViewById(R.id.enter_property_tenancy_start_date);
        this.mEndTenancyButton = (EditText) v.findViewById(R.id.enter_property_tenancy_end_date);

        this.mStartTenancyButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                showTenantStartDatePickerDialog();
            }
        });
        this.mEndTenancyButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                showTenantEndDatePickerDialog();
            }
        });

        return v;
    }

    private void initDates() {
        if (this.tenancyStartDate == null) {
            this.tenancyStartDate = Calendar.getInstance();
        }
        if (this.tenancyEndDate == null) {
            this.tenancyEndDate = Calendar.getInstance();
        }
    }

    @Override
    public void onExit(int exitCode) {
        switch (exitCode) {
        case WizardStep.EXIT_NEXT:
            saveData();
            break;
        case WizardStep.EXIT_PREVIOUS:
            saveData();
            break;
        }
    }

    private void saveData() {

    }

    public void showTenantStartDatePickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {

            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            }
        }, this.tenancyStartDate.get(Calendar.DAY_OF_YEAR), this.tenancyStartDate.get(Calendar.MONTH), this.tenancyStartDate.get(Calendar.YEAR));
        datePickerDialog.show();
        // DialogFragment newFragment = new TenacyDatePickerFragment();
        // android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        // newFragment.show(fragmentManager, "tenancyStartDatePicker");
    }

    public void showTenantEndDatePickerDialog() {
        // DialogFragment newFragment = new TenacyDatePickerFragment();
        // android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        // newFragment.show(fragmentManager, "tenacyEndDatePicker");
    }

    // public final static class TenacyDatePickerFragment extends DialogFragment
    // implements DatePickerDialog.OnDateSetListener {
    //
    // @Override
    // public Dialog onCreateDialog(Bundle savedInstanceState) {
    // // Use the current date as the default date in the picker
    // final Calendar c = Calendar.getInstance();
    // int year = c.get(Calendar.YEAR);
    // int month = c.get(Calendar.MONTH);
    // int day = c.get(Calendar.DAY_OF_MONTH);
    //
    // // Create a new instance of DatePickerDialog and return it
    // return new DatePickerDialog(getActivity(), this, year, month, day);
    // }
    //
    // public void onDateSet(DatePicker view, int year, int month, int day) {
    // AddPropertyTenantSingleUnitDetails.this.mStartTenancyButton
    // .setText(day + "/" + month + "/" + year);
    // }
    // }

    public class TenantDto {
        private String name;
        private String phone;
        private String email;

        public TenantDto(String name, String phone, String email) {
            super();
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        public String getName() {
            return this.name;
        }

        public String getPhone() {
            return this.phone;
        }

        public String getEmail() {
            return this.email;
        }

    }
}

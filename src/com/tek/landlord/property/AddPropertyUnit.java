package com.tek.landlord.property;

import java.util.ArrayList;
import java.util.HashMap;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.tek.landlord.R;

public class AddPropertyUnit extends WizardStep {

    private Button mUnitAddButton;
    private ListView mListView;

    @ContextVariable
    ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter adapter;

    @ContextVariable
    private ArrayList<TenantDto> tenantItemList;

    // Set your layout here
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_property_tenants, container, false);
        View wizardLayout = inflater.inflate(R.layout.wizard, container, false);
        initTenantList();

        this.mUnitAddButton = (Button) v.findViewById(R.id.tenant_add_button);
        this.mUnitAddButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_tenant_dialog);
                dialog.setTitle("Add Unit");

                // set the custom dialog components - text, image and button
                final EditText name = (EditText) dialog.findViewById(R.id.enter_tenant_name);
                final EditText phone = (EditText) dialog.findViewById(R.id.enter_tenant_phone);
                final EditText email = (EditText) dialog.findViewById(R.id.enter_tenant_email);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialog_tenant_button_add);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        HashMap<String, String> itemMap = new HashMap<String, String>();
                        itemMap.put("name", name.getText().toString());
                        itemMap.put("phone", phone.getText().toString());
                        itemMap.put("email", email.getText().toString());
                        AddPropertyUnit.this.itemList.add(itemMap);
                        AddPropertyUnit.this.adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        this.mListView = (ListView) v.findViewById(R.id.tenants_list);
        HashMap<String, String> itemMap = new HashMap<String, String>();

        for (TenantDto item : this.tenantItemList) {
            itemMap.put("name", item.getName());
            itemMap.put("email", item.getEmail());
            itemMap.put("phone", item.getPhone());
            this.itemList.add(itemMap);
        }

        this.adapter = new SimpleAdapter(this.getActivity(), this.itemList, R.layout.add_tenant_row, new String[] {
                "name", "phone", "email"
        }, new int[] {
                R.id.tenant_name, R.id.tenant_phone, R.id.tenant_email
        });
        this.mListView.setAdapter(this.adapter);
        return v;
    }

    private void initTenantList() {
        if (this.tenantItemList == null) {
            this.tenantItemList = new ArrayList<TenantDto>();
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
        for (HashMap<String, String> tenantEntry : this.itemList) {
            String name = tenantEntry.get("name");
            String phone = tenantEntry.get("phone");
            String email = tenantEntry.get("email");
            this.tenantItemList.add(new TenantDto(name, phone, email));
        }
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

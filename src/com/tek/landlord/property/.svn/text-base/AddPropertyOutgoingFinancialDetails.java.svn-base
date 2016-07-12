package com.tek.landlord.property;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

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
import android.widget.Spinner;

import com.tek.landlord.R;
import com.tek.landlord.dto.FinancialItemType;
import com.tek.landlord.dto.TimeIntervalDto;

public class AddPropertyOutgoingFinancialDetails extends WizardStep {

    private ListView mListView;
    private Button mFinancialAddButton;
    private SimpleAdapter adapter;
    @ContextVariable
    private ArrayList<FinancialItemDto> financialItemList;

    @ContextVariable
    ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();

    private String mCurrency;

    // Set your layout here
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_property_financial, container, false);

        if (this.financialItemList == null) {
            this.financialItemList = new ArrayList<FinancialItemDto>();
        }

        this.mCurrency = Currency.getInstance(Locale.getDefault()).getSymbol();

        this.mFinancialAddButton = (Button) v.findViewById(R.id.financial_add);
        this.mFinancialAddButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_financial_dialog);
                dialog.setTitle("Add outgoing");

                // set the custom dialog components - text, image and button
                final EditText amount = (EditText) dialog.findViewById(R.id.enter_financial_item_amount);
                final Spinner type = (Spinner) dialog.findViewById(R.id.enter_financial_type);
                final Spinner timeInterval = (Spinner) dialog.findViewById(R.id.enter_financial_time_interval);

                // text.setText("Android custom dialog example!");
                // ImageView image = (ImageView) dialog.findViewById(R.id.image);
                // image.setImageResource(R.drawable.ic_launcher);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialog_finanical_button_add);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        HashMap<String, String> itemMap = new HashMap<String, String>();
                        itemMap.put("currency", AddPropertyOutgoingFinancialDetails.this.mCurrency);
                        itemMap.put("amount", amount.getText().toString());
                        itemMap.put("type", (String) type.getSelectedItem());
                        itemMap.put("time_interval", (String) timeInterval.getSelectedItem());
                        AddPropertyOutgoingFinancialDetails.this.itemList.add(itemMap);
                        AddPropertyOutgoingFinancialDetails.this.adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        this.mListView = (ListView) v.findViewById(R.id.financial_list);
        HashMap<String, String> itemMap = new HashMap<String, String>();

        for (FinancialItemDto item : this.financialItemList) {
            itemMap.put("currency", this.mCurrency);
            itemMap.put("amount", item.getAmount());
            itemMap.put("type", item.getType().getDescription());
            itemMap.put("time_interval", item.getInterval().getDescription());
            this.itemList.add(itemMap);
        }

        this.adapter = new SimpleAdapter(this.getActivity(), this.itemList, R.layout.financial_item_row, new String[] {
                "currency", "amount", "type", "time_interval"
        }, new int[] {
                R.id.financial_amount_sign, R.id.financial_amount, R.id.financial_type, R.id.financial_time_interval
        });
        this.mListView.setAdapter(this.adapter);
        return v;
    }

    @Override
    public void onExit(int exitCode) {
        switch (exitCode) {
        case WizardStep.EXIT_NEXT:
            break;
        case WizardStep.EXIT_PREVIOUS:
            bindDataFields();
            break;
        }
    }

    private void bindDataFields() {
        for (HashMap<String, String> financialentry : this.itemList) {
            String amount = financialentry.get("amount");
            String type = financialentry.get("type");
            String timeInterval = financialentry.get("time_interval");
            this.financialItemList.add(new FinancialItemDto(amount, FinancialItemType.fromDescription(type), TimeIntervalDto.fromDescription(timeInterval)));
        }
    }

    public class FinancialItemDto {
        private String amount;
        private FinancialItemType type;
        private TimeIntervalDto interval;

        public FinancialItemDto(String description, FinancialItemType type, TimeIntervalDto interval) {
            super();
            this.amount = description;
            this.type = type;
            this.interval = interval;
        }

        public String getAmount() {
            return this.amount;
        }

        public void setDescription(String amount) {
            this.amount = amount;
        }

        public FinancialItemType getType() {
            return this.type;
        }

        public void setType(FinancialItemType type) {
            this.type = type;
        }

        public TimeIntervalDto getInterval() {
            return this.interval;
        }

        public void setInterval(TimeIntervalDto interval) {
            this.interval = interval;
        }
    }
}

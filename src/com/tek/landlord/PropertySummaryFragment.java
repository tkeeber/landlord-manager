package com.tek.landlord;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tek.landlord.dao.PropertyDao;
import com.tek.landlord.property.AddPropertyWizard;

public class PropertySummaryFragment extends Fragment {

    private static final int ID_ADD = 1;

    private ActionItem addItem;
    private QuickAction mQuickAction;

    private TextView mTotalProperties;
    private TextView mTotalOccuppied;
    private TextView mTotalAvailable;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.property_summary_fragment, container, false);
        this.mTotalProperties = (TextView) view.findViewById(R.id.total_properties);
        populateFields();

        View layoutView = view.findViewById(R.id.property_summary_layout);
        layoutView.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                PropertySummaryFragment.this.mQuickAction.show(v);
            }
        });
        this.addItem = new ActionItem(ID_ADD, "Add Property", getResources().getDrawable(R.drawable.plus_large));
        this.mQuickAction = new QuickAction(this.getActivity(), QuickAction.HORIZONTAL);
        this.mQuickAction.addActionItem(this.addItem);
        this.mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {

            public void onItemClick(QuickAction quickAction, int pos, int actionId) {
                ActionItem actionItem = quickAction.getActionItem(pos);
                if (actionId == ID_ADD) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, new AddPropertyWizard()).commit();
                } else {
                    Toast.makeText(PropertySummaryFragment.this.getActivity(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void populateFields() {
        PropertyDao propertyDao = new PropertyDao(this.getActivity());
        propertyDao.open();
        int propertiesCount = propertyDao.getPropertiesCount();
        propertyDao.close();
        this.mTotalProperties.setText(String.valueOf(propertiesCount));
    }

}

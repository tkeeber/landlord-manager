package com.tek.landlord;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tek.landlord.property.AddPropertyWizard;

public class PropertyFragment extends Fragment{

    private static final int ID_ADD = 1;
    private static final int ID_ACCEPT = 2;
    private static final int ID_EDIT = 3;
    private static final int ID_UPLOAD = 4;

    QuickAction mQuickAction;

    ActionItem addItem;
    ActionItem acceptItem;
    ActionItem editItem;
    
  
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	   PropertySummaryFragment propertySummaryFragment = new PropertySummaryFragment();
           PropertyListFragment propertyListFragment = new PropertyListFragment();
           FragmentManager fragmentManager = getFragmentManager();
           fragmentManager.beginTransaction().replace(R.id.property_summary_container, propertySummaryFragment).commit();
           fragmentManager.beginTransaction().replace(R.id.property_list_container, propertyListFragment).commit();

        return inflater.inflate(R.layout.property_fragment, container, false);
    }

    private void initNewsfeed() {
    	//addItem = new ActionItem(ID_ADD, "Add Property", getResources().getDrawable(R.drawable.plus_large));
    	//acceptItem = new ActionItem(ID_ACCEPT, "Dismiss", getResources().getDrawable(R.drawable.ok));
    	editItem = new ActionItem(ID_EDIT, "Edit", getResources().getDrawable(R.drawable.document));

//        ActionItem addItem = new ActionItem(ID_ADD, "Paid", getResources().getDrawable(R.drawable.ok));
//        ActionItem acceptItem = new ActionItem(ID_ACCEPT, "Partial", getResources().getDrawable(R.drawable.calculator));
//        ActionItem uploadItem = new ActionItem(ID_UPLOAD, "Remind Tenant", getResources().getDrawable(R.drawable.notification));

        this.mQuickAction = new QuickAction(this.getActivity(), QuickAction.HORIZONTAL);

//        this.mQuickAction.addActionItem(addItem);
//        this.mQuickAction.addActionItem(acceptItem);
//        this.mQuickAction.addActionItem(uploadItem);

        // setup the action item click listener
        this.mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {

            public void onItemClick(QuickAction quickAction, int pos, int actionId) {
                ActionItem actionItem = quickAction.getActionItem(pos);
                if (actionId == ID_ADD) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, new AddPropertyWizard()).commit();
                } else {
                    Toast.makeText(PropertyFragment.this.getActivity(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.mQuickAction.setOnDismissListener(new QuickAction.OnDismissListener() {

            public void onDismiss() {
                //Toast.makeText(NewsfeedFragment.this.getActivity(), "Ups..dismissed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

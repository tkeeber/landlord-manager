package com.tek.landlord;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tek.landlord.dao.PropertyDao;
import com.tek.landlord.property.AddPropertyWizard;
import com.tek.landlord.property.PropertyListAdaptor;

public class PropertyListFragment extends ListFragment {

    private static final int ID_ADD = 1;
    private static final int ID_ACCEPT = 2;
    private static final int ID_EDIT = 3;
    private static final int ID_UPLOAD = 4;

    QuickAction mQuickAction;

    private PropertyDao propertyDao;

    ActionItem addItem;
    ActionItem acceptItem;
    ActionItem editItem;
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPropertyList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.propertyDao.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.property_list_fragment, container, false);
    }

    private void initPropertyList() {
    	//addItem = new ActionItem(ID_ADD, "Add Property", getResources().getDrawable(R.drawable.plus_large));
    	//acceptItem = new ActionItem(ID_ACCEPT, "Dismiss", getResources().getDrawable(R.drawable.ok));
    	editItem = new ActionItem(ID_EDIT, "Edit", getResources().getDrawable(R.drawable.document));
        this.propertyDao = new PropertyDao(getActivity());
        this.propertyDao.open();
        Cursor cursor = this.propertyDao.getProperties();
       PropertyListAdaptor adapter = new PropertyListAdaptor(getActivity(), cursor);
        setListAdapter(adapter);

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
                    Toast.makeText(PropertyListFragment.this.getActivity(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
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

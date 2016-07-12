package com.tek.landlord;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.itemmanipulation.AnimateDismissAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.tek.landlord.adapter.NewsfeedListAdapter;
import com.tek.landlord.dao.NewsfeedDao;
import com.tek.landlord.domain.NewsfeedItem;
import com.tek.landlord.domain.NewsfeedItem.NewsfeedEntry;
import com.tek.landlord.domain.NewsfeedItemBuilder;
import com.tek.landlord.domain.NewsfeedType;
import com.tek.landlord.wizard.NewPropertyWizardActivity;

public class NewsfeedFragment extends ListFragment {

    private static final int ID_ADD = 1;
    private static final int ID_ACCEPT = 2;
    private static final int ID_EDIT = 3;

    QuickAction mQuickAction;

    private NewsfeedDao newsfeedDao;
    private ListView mListView;

    ActionItem addItem;
    ActionItem acceptItem;
    ActionItem editItem;
    private AnimateDismissAdapter animateDismissAdapter;
    private NewsfeedListAdapter adapter;

    private List<NewsfeedItem> newsfeedItems;

    private int mSelectedRow;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initNewsfeed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.newsfeedDao.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfeed_fragment, container, false);
        this.mListView = (ListView) view.findViewById(R.id.list_item);
        return view;
    }

    private void initNewsfeed() {
        this.addItem = new ActionItem(ID_ADD, "Add Property", getResources().getDrawable(R.drawable.plus_large));
        this.acceptItem = new ActionItem(ID_ACCEPT, "Dismiss Message", getResources().getDrawable(R.drawable.cancel));
        this.editItem = new ActionItem(ID_EDIT, "Edit", getResources().getDrawable(R.drawable.document));
        this.newsfeedDao = new NewsfeedDao(getActivity());
        this.newsfeedDao.open();
        Cursor cursor = this.newsfeedDao.getAllNonDismissedNewsfeedItems();

        this.newsfeedItems = new ArrayList<NewsfeedItem>();

        if (cursor.moveToFirst()) {
            do {
                NewsfeedItem newsfeedItem = NewsfeedItemBuilder.aNewsfeedItem()
                    .withTitle(cursor.getString(cursor.getColumnIndex(NewsfeedEntry.COLUMN_NAME_TITLE)))
                    .withId(cursor.getLong(cursor.getColumnIndex(NewsfeedEntry._ID)))
                    .withDescription(cursor.getString(cursor.getColumnIndex(NewsfeedEntry.COLUMN_NAME_DESCRIPTION_TEXT)))
                    .withDate(cursor.getString(cursor.getColumnIndex(NewsfeedEntry.COLUMN_NAME_DATE)))
                    .withType(cursor.getInt(cursor.getColumnIndex(NewsfeedEntry.COLUMN_NAME_TYPE)))
                    .build();

                this.newsfeedItems.add(newsfeedItem);
            } while (cursor.moveToNext());
        }

        this.adapter = new NewsfeedListAdapter(getActivity(), this.newsfeedItems);
        OnDismissCallback myOnDismissCallback = new OnDismissCallback() {
            public void onDismiss(AbsListView view, int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    removeItem(position);
                }
            }
        };
        this.animateDismissAdapter = new AnimateDismissAdapter(this.adapter, myOnDismissCallback);
        this.animateDismissAdapter.setAbsListView(this.mListView);
        setListAdapter(this.animateDismissAdapter);

        // ActionItem addItem = new ActionItem(ID_ADD, "Paid", getResources().getDrawable(R.drawable.ok));
        // ActionItem acceptItem = new ActionItem(ID_ACCEPT, "Partial", getResources().getDrawable(R.drawable.calculator));
        // ActionItem uploadItem = new ActionItem(ID_UPLOAD, "Remind Tenant", getResources().getDrawable(R.drawable.notification));

        this.mQuickAction = new QuickAction(this.getActivity(), QuickAction.HORIZONTAL);

        // this.mQuickAction.addActionItem(addItem);
        // this.mQuickAction.addActionItem(acceptItem);
        // this.mQuickAction.addActionItem(uploadItem);

        // setup the action item click listener
        this.mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {

            public void onItemClick(QuickAction quickAction, int pos, int actionId) {
                ActionItem actionItem = quickAction.getActionItem(pos);
                if (actionId == ID_ADD) {
                    // FragmentManager fragmentManager = getFragmentManager();
                    // fragmentManager.beginTransaction().replace(R.id.container, new AddPropertyWizard()).commit();
                    startAddProperty();
                } else if (actionId == ID_ACCEPT) {
                    removeItem(NewsfeedFragment.this.mSelectedRow);
                }
            }
        });

        this.mQuickAction.setOnDismissListener(new QuickAction.OnDismissListener() {

            public void onDismiss() {
                // Toast.makeText(NewsfeedFragment.this.getActivity(), "Ups..dismissed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void removeItem(final int selectionPosition) {
        long id = this.adapter.get(selectionPosition).getId();
        this.adapter.remove(selectionPosition);
        this.newsfeedDao.markItemAsRead(id);
        this.adapter.notifyDataSetChanged();

        Toast.makeText(NewsfeedFragment.this.getActivity(), "Removed newsfeed item (" + id + ") at postion (" + selectionPosition + ")", Toast.LENGTH_SHORT)
            .show();

    }

    private void startAddProperty() {
        startActivity(new Intent(this.getActivity(), NewPropertyWizardActivity.class));
    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
        this.mSelectedRow = position;
        int newsfeedRowType = this.adapter.get(position).getType();
        this.mQuickAction.clearActionItems();
        switch (newsfeedRowType) {
        case NewsfeedType.APPLICATION_MESSAGE_ID:
            this.mQuickAction.addActionItem(this.addItem);
            this.mQuickAction.addActionItem(this.acceptItem);
            break;
        case NewsfeedType.NEW_PROPERTY_ADDED_ID:
            this.mQuickAction.addActionItem(this.editItem);
            this.mQuickAction.addActionItem(this.acceptItem);
            break;
        }

        this.mQuickAction.show(view);
    }
}

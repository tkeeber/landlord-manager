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

package com.tek.landlord.wizard.framework;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tek.landlord.R;

public class PropertyTypeChoiceFragment extends ListFragment {
    private static final String ARG_KEY = "key";

    private PageFragmentCallbacks mCallbacks;
    private List<String> mChoices;
    private String mKey;
    private Page mPage;

    public static PropertyTypeChoiceFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        PropertyTypeChoiceFragment fragment = new PropertyTypeChoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public PropertyTypeChoiceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        this.mKey = args.getString(ARG_KEY);
        this.mPage = this.mCallbacks.onGetPage(this.mKey);

        PropertyTypeChoicePage fixedChoicePage = (PropertyTypeChoicePage) this.mPage;
        this.mChoices = new ArrayList<String>();
        for (int i = 0; i < fixedChoicePage.getOptionCount(); i++) {
            this.mChoices.add(fixedChoicePage.getOptionAt(i));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wizard_framework_fragment_page, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(this.mPage.getTitle());

        final ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice, android.R.id.text1, this.mChoices));
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Pre-select currently selected item.
        new Handler().post(new Runnable() {

            public void run() {
                String selection = PropertyTypeChoiceFragment.this.mPage.getData().getString(PropertyTypeChoicePage.PROPERTY_TYPE_KEY);
                for (int i = 0; i < PropertyTypeChoiceFragment.this.mChoices.size(); i++) {
                    if (PropertyTypeChoiceFragment.this.mChoices.get(i).equals(selection)) {
                        listView.setItemChecked(i, true);
                        break;
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof PageFragmentCallbacks)) {
            throw new ClassCastException("Activity must implement PageFragmentCallbacks");
        }

        this.mCallbacks = (PageFragmentCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mCallbacks = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        this.mPage.getData().putString(Page.SIMPLE_DATA_KEY, getListAdapter().getItem(position).toString());

        this.mPage.getData().putString(PropertyTypeChoicePage.PROPERTY_TYPE_KEY, getListAdapter().getItem(position).toString());
        this.mPage.notifyDataChanged();
    }
}

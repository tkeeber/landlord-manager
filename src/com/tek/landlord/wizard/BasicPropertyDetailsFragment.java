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

package com.tek.landlord.wizard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.tek.landlord.R;
import com.tek.landlord.wizard.framework.PageFragmentCallbacks;

public class BasicPropertyDetailsFragment extends Fragment {
    private static final String ARG_KEY = "basic_property_details_key";

    private PageFragmentCallbacks mCallbacks;
    private String mKey;
    private BasicPropertDetailsPage mPage;

    private TextView mNameView;
    private TextView mAddressView;
    private TextView mCityView;
    private TextView mStateView;
    private TextView mPostCodeView;
    private Spinner mCountryView;

    public static BasicPropertyDetailsFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        BasicPropertyDetailsFragment fragment = new BasicPropertyDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public BasicPropertyDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        this.mKey = args.getString(ARG_KEY);
        this.mPage = (BasicPropertDetailsPage) this.mCallbacks.onGetPage(this.mKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_property_basic, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(this.mPage.getTitle());

        this.mNameView = ((TextView) rootView.findViewById(R.id.enter_property_name));
        this.mNameView.setText(this.mPage.getData().getString(BasicPropertDetailsPage.NAME_DATA_KEY));

        this.mAddressView = ((TextView) rootView.findViewById(R.id.enter_property_address));
        this.mAddressView.setText(this.mPage.getData().getString(BasicPropertDetailsPage.ADDRESS_DATA_KEY));

        this.mCityView = ((TextView) rootView.findViewById(R.id.enter_property_city));
        this.mCityView.setText(this.mPage.getData().getString(BasicPropertDetailsPage.CITY_DATA_KEY));

        this.mStateView = ((TextView) rootView.findViewById(R.id.enter_property_state_county));
        this.mStateView.setText(this.mPage.getData().getString(BasicPropertDetailsPage.STATE_DATA_KEY));

        this.mPostCodeView = ((TextView) rootView.findViewById(R.id.enter_property_postcode));
        this.mPostCodeView.setText(this.mPage.getData().getString(BasicPropertDetailsPage.POSTCODE_DATA_KEY));

        this.mCountryView = (Spinner) rootView.findViewById(R.id.enter_property_country);
        // TODO; set selected country from text.
        // this.mCountryView.setSelection(position);
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.mNameView.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                BasicPropertyDetailsFragment.this.mPage.getData().putString(BasicPropertDetailsPage.NAME_DATA_KEY,
                        (editable != null) ? editable.toString() : null);
                BasicPropertyDetailsFragment.this.mPage.notifyDataChanged();
            }
        });

        this.mAddressView.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                BasicPropertyDetailsFragment.this.mPage.getData().putString(BasicPropertDetailsPage.ADDRESS_DATA_KEY,
                        (editable != null) ? editable.toString() : null);
                BasicPropertyDetailsFragment.this.mPage.notifyDataChanged();
            }
        });

        this.mCityView.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                BasicPropertyDetailsFragment.this.mPage.getData().putString(BasicPropertDetailsPage.CITY_DATA_KEY,
                        (editable != null) ? editable.toString() : null);
                BasicPropertyDetailsFragment.this.mPage.notifyDataChanged();
            }
        });
        this.mStateView.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                BasicPropertyDetailsFragment.this.mPage.getData().putString(BasicPropertDetailsPage.STATE_DATA_KEY,
                        (editable != null) ? editable.toString() : null);
                BasicPropertyDetailsFragment.this.mPage.notifyDataChanged();
            }
        });
        this.mPostCodeView.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                BasicPropertyDetailsFragment.this.mPage.getData().putString(BasicPropertDetailsPage.POSTCODE_DATA_KEY,
                        (editable != null) ? editable.toString() : null);
                BasicPropertyDetailsFragment.this.mPage.notifyDataChanged();
            }
        });

        this.mCountryView.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BasicPropertyDetailsFragment.this.mPage.getData().putString(BasicPropertDetailsPage.COUNTRY_DATA_KEY,
                        BasicPropertyDetailsFragment.this.mCountryView.getSelectedItem().toString());
                BasicPropertyDetailsFragment.this.mPage.notifyDataChanged();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        // In a future update to the support library, this should override setUserVisibleHint
        // instead of setMenuVisibility.
        if (this.mNameView != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (!menuVisible) {
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        }
    }
}

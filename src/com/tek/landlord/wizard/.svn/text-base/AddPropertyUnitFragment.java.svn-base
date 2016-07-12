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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.itemmanipulation.AnimateDismissAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import com.tek.landlord.R;
import com.tek.landlord.domain.Property;
import com.tek.landlord.domain.PropertyBuilder;
import com.tek.landlord.domain.PropertyType;
import com.tek.landlord.wizard.framework.PageFragmentCallbacks;

public class AddPropertyUnitFragment extends Fragment implements
		ListRemoveCallback {
	private static final String ARG_KEY = "add_unit_key";

	private PageFragmentCallbacks mCallbacks;
	private String mKey;
	private AddPropertyUnitPage mPage;
	private TextView mNameView;
	ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();

	private ImageButton mUnitAddButton;

	private UnitRowAdapter adapter;
	private ArrayList<String> unitItemList;

	private ListView mListView;

	private AnimateDismissAdapter animateDismissAdapter;
	private SwingLeftInAnimationAdapter alphaInAnimationAdapter;

	public static AddPropertyUnitFragment create(String key) {
		Bundle args = new Bundle();
		args.putString(ARG_KEY, key);

		AddPropertyUnitFragment fragment = new AddPropertyUnitFragment();
		fragment.setArguments(args);
		return fragment;
	}

	public AddPropertyUnitFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle args = getArguments();
		this.mKey = args.getString(ARG_KEY);
		this.mPage = (AddPropertyUnitPage) this.mCallbacks.onGetPage(this.mKey);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.wizard_framework_add_property_unit, container, false);
		((TextView) rootView.findViewById(R.id.text_property_title_unit))
				.setText(this.mPage.getTitle());

		if (this.unitItemList == null) {
			this.unitItemList = new ArrayList<String>();
		}

		this.mUnitAddButton = (ImageButton) rootView
				.findViewById(R.id.unit_add_button);
		this.mUnitAddButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onAddUnitClick();
			}
		});

		this.mListView = (ListView) rootView.findViewById(R.id.units_list);
		this.adapter = new UnitRowAdapter(this.getActivity(), R.id.unit_name,
				this.unitItemList, this);

		this.alphaInAnimationAdapter = new SwingLeftInAnimationAdapter(
				this.adapter);
		this.alphaInAnimationAdapter.setAnimationDelayMillis(500);
		this.alphaInAnimationAdapter.setAbsListView(this.mListView);

		OnDismissCallback myOnDismissCallback = new OnDismissCallback() {
			public void onDismiss(AbsListView view, int[] reverseSortedPositions) {
				for (int position : reverseSortedPositions) {
					removeItem(position);
				}
			}
		};
		this.animateDismissAdapter = new AnimateDismissAdapter(
				this.alphaInAnimationAdapter, myOnDismissCallback);
		this.animateDismissAdapter.setAbsListView(this.mListView);
		this.mListView.setAdapter(this.animateDismissAdapter);
		return rootView;
	}

	public void removeItem(final int position) {
		AddPropertyUnitFragment.this.adapter.remove(position);
		this.alphaInAnimationAdapter.notifyDataSetChanged();
		this.alphaInAnimationAdapter.setShouldAnimateFromPosition(position);
		this.adapter.notifyDataSetChanged();
	}

	public void remove(int position) {
		this.animateDismissAdapter.animateDismiss(position);
	}

	public void onAddUnitClick() {
		// custom dialog
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.add_unit_dialog);
		dialog.setTitle("Add Unit");

		// set the custom dialog components - text, image and button
		final EditText name = (EditText) dialog
				.findViewById(R.id.enter_unit_name);
		final EditText totalBedrooms = (EditText) dialog
				.findViewById(R.id.enter_unit_bedrooms);
		final EditText totalBathrooms = (EditText) dialog
				.findViewById(R.id.enter_unit_bathrooms);
		final EditText rent = (EditText) dialog
				.findViewById(R.id.enter_unit_rent);
		final EditText notes = (EditText) dialog
				.findViewById(R.id.enter_unit_notes);

		Button dialogButton = (Button) dialog
				.findViewById(R.id.dialog_tenant_button_add);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				AddPropertyUnitFragment.this.unitItemList.add(name.getText()
						.toString());
				AddPropertyUnitFragment.this.adapter.notifyDataSetChanged();
				AddPropertyUnitFragment.this.mPage.getData().putInt(
						AddPropertyUnitPage.NUMBER_OF_UNITS,
						AddPropertyUnitFragment.this.unitItemList.size());
				Double rentAmount = Double.valueOf(rent.getText().toString());
				if (rentAmount == null) {
					rentAmount = 0.0;
				}
				Property unit = PropertyBuilder.aProperty()
						.withType(PropertyType.UNIT_FLAT.getIdentifier())
						.withName(name.getText().toString())
						.withRentAmount(rentAmount).build();
				AddPropertyUnitFragment.this.mPage.getData()
						.putParcelableArrayList(AddPropertyUnitPage.UNITS,
								getOrCreateListAndAdd(unit));
				AddPropertyUnitFragment.this.mPage.notifyDataChanged();
				dialog.dismiss();
			}

			private ArrayList<Property> getOrCreateListAndAdd(Property property) {
				ArrayList<Property> unitsList = AddPropertyUnitFragment.this.mPage
						.getData().getParcelableArrayList(
								AddPropertyUnitPage.UNITS);
				if (unitsList == null) {
					unitsList = new ArrayList<Property>();
				}
				unitsList.add(property);
				return unitsList;
			}
		});

		dialog.show();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (!(activity instanceof PageFragmentCallbacks)) {
			throw new ClassCastException(
					"Activity must implement PageFragmentCallbacks");
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
	}

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);

		// In a future update to the support library, this should override
		// setUserVisibleHint
		// instead of setMenuVisibility.
		if (this.mNameView != null) {
			InputMethodManager imm = (InputMethodManager) getActivity()
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			if (!menuVisible) {
				imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
			}
		}
	}
}

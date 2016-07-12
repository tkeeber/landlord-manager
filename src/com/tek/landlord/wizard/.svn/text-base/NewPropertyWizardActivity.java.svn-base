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

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.tek.landlord.R;
import com.tek.landlord.domain.Property;
import com.tek.landlord.domain.PropertyBuilder;
import com.tek.landlord.domain.PropertyType;
import com.tek.landlord.service.PropertyService;
import com.tek.landlord.wizard.framework.AbstractWizardModel;
import com.tek.landlord.wizard.framework.ModelCallbacks;
import com.tek.landlord.wizard.framework.Page;
import com.tek.landlord.wizard.framework.PageFragmentCallbacks;
import com.tek.landlord.wizard.framework.PropertyTypeChoicePage;
import com.tek.landlord.wizard.framework.ReviewFragment;
import com.tek.landlord.wizard.framework.StepPagerStrip;

public class NewPropertyWizardActivity extends FragmentActivity implements
		PageFragmentCallbacks, ReviewFragment.Callbacks, ModelCallbacks {
	private ViewPager mPager;
	private MyPagerAdapter mPagerAdapter;

	private boolean mEditingAfterReview;

	private NewPropertyWizardModel mWizardModel = new NewPropertyWizardModel(
			this);

	private boolean mConsumePageSelectedEvent;

	private Button mNextButton;
	private Button mPrevButton;

	private List<Page> mCurrentPageSequence;
	private StepPagerStrip mStepPagerStrip;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wizard_framework_new_property);

		if (savedInstanceState != null) {
			this.mWizardModel.load(savedInstanceState.getBundle("model"));
		}

		this.mWizardModel.registerListener(this);

		this.mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		this.mPager = (ViewPager) findViewById(R.id.pager);
		this.mPager.setAdapter(this.mPagerAdapter);
		this.mStepPagerStrip = (StepPagerStrip) findViewById(R.id.strip);
		this.mStepPagerStrip
				.setOnPageSelectedListener(new StepPagerStrip.OnPageSelectedListener() {

					public void onPageStripSelected(int position) {
						position = Math.min(
								NewPropertyWizardActivity.this.mPagerAdapter
										.getCount() - 1, position);
						if (NewPropertyWizardActivity.this.mPager
								.getCurrentItem() != position) {
							NewPropertyWizardActivity.this.mPager
									.setCurrentItem(position);
						}
					}
				});

		this.mNextButton = (Button) findViewById(R.id.next_button);
		this.mPrevButton = (Button) findViewById(R.id.prev_button);

		this.mPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						NewPropertyWizardActivity.this.mStepPagerStrip
								.setCurrentPage(position);

						if (NewPropertyWizardActivity.this.mConsumePageSelectedEvent) {
							NewPropertyWizardActivity.this.mConsumePageSelectedEvent = false;
							return;
						}

						NewPropertyWizardActivity.this.mEditingAfterReview = false;
						updateBottomBar();
					}
				});

		this.mNextButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				if (NewPropertyWizardActivity.this.mPager.getCurrentItem() == NewPropertyWizardActivity.this.mCurrentPageSequence
						.size()) {
					//
					saveProperty();
				} else {
					if (NewPropertyWizardActivity.this.mEditingAfterReview) {
						NewPropertyWizardActivity.this.mPager
								.setCurrentItem(NewPropertyWizardActivity.this.mPagerAdapter
										.getCount() - 1);
					} else {
						NewPropertyWizardActivity.this.mPager
								.setCurrentItem(NewPropertyWizardActivity.this.mPager
										.getCurrentItem() + 1);
					}
				}
			}
		});

		this.mPrevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				NewPropertyWizardActivity.this.mPager
						.setCurrentItem(NewPropertyWizardActivity.this.mPager
								.getCurrentItem() - 1);
			}
		});

		onPageTreeChanged();
		updateBottomBar();
	}

	public void saveProperty() {

		/**
		 * bundle:Bundle[{unit-bundle-key=Bundle[{number-of-units=2}],
		 * Multi-unit house (Flats):Property Details=Bundle[{state=Yorkshire,
		 * postcode=LS28 5rz, address=18 Warrels Avenue, city=Leeds,
		 * name=Warrels, country=United Kingdom}], Property
		 * type=Bundle[{_=Multi-unit house (Flats), property-type=Multi-unit
		 * house (Flats)}]}] 06-04 15:18:27.038: I/System.out(546):
		 * propertyBundle:Bundle[{state=Yorkshire, postcode=LS28 5rz, address=18
		 * Warrels Avenue, city=Leeds, name=Warrels, country=United Kingdom}]
		 * 06-04 15:18:27.048: I/System.out(546):
		 * unitBundle:Bundle[{number-of-units=2}]
		 */

		Bundle bundle = this.mWizardModel.save();
		Toast.makeText(getApplicationContext(), "bundle:" + bundle,
				Toast.LENGTH_LONG).show();
		System.out.println("bundle:" + bundle);

		Bundle typeBundle = bundle
				.getBundle(PropertyTypeChoicePage.PROPERTY_TYPE_PAGE_TITLE);
		String propertyTypeString = typeBundle
				.getString(PropertyTypeChoicePage.PROPERTY_TYPE_KEY);
		PropertyType propertyType = PropertyType.from(propertyTypeString);

		Bundle propertyBundle = null;
		if (propertyType.equals(PropertyType.MULITPLE_UNIT_HOUSE)) {
			propertyBundle = bundle
					.getBundle(BasicPropertDetailsPage.MULTI_BASIC_DETAILS_BUNDLE_KEY);
		} else if (propertyType.equals(PropertyType.SINGLE_TENANTED_HOUSE)) {
			propertyBundle = bundle
					.getBundle(BasicPropertDetailsPage.SINGLE_BASIC_DETAILS_BUNDLE_KEY);
		}

		System.out.println("propertyBundle:" + propertyBundle);

		String propertyName = propertyBundle
				.getString(BasicPropertDetailsPage.NAME_DATA_KEY);
		String propertyAddress = propertyBundle
				.getString(BasicPropertDetailsPage.ADDRESS_DATA_KEY);
		String propertyCity = propertyBundle
				.getString(BasicPropertDetailsPage.CITY_DATA_KEY);
		String propertyState = propertyBundle
				.getString(BasicPropertDetailsPage.STATE_DATA_KEY);
		String propertyCountry = propertyBundle
				.getString(BasicPropertDetailsPage.COUNTRY_DATA_KEY);
		String propertyPostCode = propertyBundle
				.getString(BasicPropertDetailsPage.POSTCODE_DATA_KEY);
		Property property = PropertyBuilder.aProperty().withName(propertyName)
				.withAddress(propertyAddress).withCity(propertyCity)
				.withState(propertyState).withCountry(propertyCountry)
				.withPostCode(propertyPostCode)
				.withType(propertyType.getIdentifier()).build();

		if (propertyType == PropertyType.MULITPLE_UNIT_HOUSE) {
			Bundle unitBundle = bundle
					.getBundle(AddPropertyUnitPage.UNIT_BUNDLE_KEY);
			System.out.println("unitBundle:" + unitBundle);
			List<Property> units = unitBundle
					.getParcelableArrayList(AddPropertyUnitPage.UNITS);

			PropertyService propertyService = new PropertyService(
					this.getBaseContext());
			propertyService.createProperty(property, units);
		}
		// PropertyDao propertyDao = new PropertyDao(this);
		// propertyDao.open();
		// long propertyId = propertyDao.createProperty(property);
		// List<Property> units = this.mWizardModel.getUnitsToSave(propertyId);
		// propertyDao.close();
	}

	public void onPageTreeChanged() {
		this.mCurrentPageSequence = this.mWizardModel.getCurrentPageSequence();
		recalculateCutOffPage();
		this.mStepPagerStrip.setPageCount(this.mCurrentPageSequence.size() + 1); // +
		this.mPagerAdapter.notifyDataSetChanged();
		updateBottomBar();
	}

	private void updateBottomBar() {
		int position = this.mPager.getCurrentItem();
		if (position == this.mCurrentPageSequence.size()) {
			this.mNextButton.setText(R.string.finish);
			this.mNextButton
					.setBackgroundResource(R.drawable.finish_background);
			this.mNextButton.setTextAppearance(this,
					R.style.TextAppearanceFinish);
		} else {
			this.mNextButton.setText(this.mEditingAfterReview ? R.string.review
					: R.string.next);
			this.mNextButton
					.setBackgroundResource(R.drawable.selectable_item_background);
			TypedValue v = new TypedValue();
			getTheme().resolveAttribute(android.R.attr.textAppearanceMedium, v,
					true);
			this.mNextButton.setTextAppearance(this, v.resourceId);
			this.mNextButton.setEnabled(position != this.mPagerAdapter
					.getCutOffPage());
		}

		this.mPrevButton.setVisibility(position <= 0 ? View.INVISIBLE
				: View.VISIBLE);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.mWizardModel.unregisterListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBundle("model", this.mWizardModel.save());
	}

	public AbstractWizardModel onGetModel() {
		return this.mWizardModel;
	}

	public void onEditScreenAfterReview(String key) {
		for (int i = this.mCurrentPageSequence.size() - 1; i >= 0; i--) {
			if (this.mCurrentPageSequence.get(i).getKey().equals(key)) {
				this.mConsumePageSelectedEvent = true;
				this.mEditingAfterReview = true;
				this.mPager.setCurrentItem(i);
				updateBottomBar();
				break;
			}
		}
	}

	public void onPageDataChanged(Page page) {
		if (page.isRequired()) {
			if (recalculateCutOffPage()) {
				this.mPagerAdapter.notifyDataSetChanged();
				updateBottomBar();
			}
		}
	}

	public Page onGetPage(String key) {
		return this.mWizardModel.findByKey(key);
	}

	private boolean recalculateCutOffPage() {
		// Cut off the pager adapter at first required page that isn't completed
		int cutOffPage = this.mCurrentPageSequence.size() + 1;
		for (int i = 0; i < this.mCurrentPageSequence.size(); i++) {
			Page page = this.mCurrentPageSequence.get(i);
			if (page.isRequired() && !page.isCompleted()) {
				cutOffPage = i;
				break;
			}
		}

		if (this.mPagerAdapter.getCutOffPage() != cutOffPage) {
			this.mPagerAdapter.setCutOffPage(cutOffPage);
			return true;
		}

		return false;
	}

	public class MyPagerAdapter extends FragmentStatePagerAdapter {
		private int mCutOffPage;
		private Fragment mPrimaryItem;

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			if (i >= NewPropertyWizardActivity.this.mCurrentPageSequence.size()) {
				return new ReviewFragment();
			}

			return NewPropertyWizardActivity.this.mCurrentPageSequence.get(i)
					.createFragment();
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO: be smarter about this
			if (object == this.mPrimaryItem) {
				// Re-use the current fragment (its position never changes)
				return POSITION_UNCHANGED;
			}

			return POSITION_NONE;
		}

		@Override
		public void setPrimaryItem(ViewGroup container, int position,
				Object object) {
			super.setPrimaryItem(container, position, object);
			this.mPrimaryItem = (Fragment) object;
		}

		@Override
		public int getCount() {
			if (NewPropertyWizardActivity.this.mCurrentPageSequence == null) {
				return 0;
			}
			return Math
					.min(this.mCutOffPage + 1,
							NewPropertyWizardActivity.this.mCurrentPageSequence
									.size() + 1);
		}

		public void setCutOffPage(int cutOffPage) {
			if (cutOffPage < 0) {
				cutOffPage = Integer.MAX_VALUE;
			}
			this.mCutOffPage = cutOffPage;
		}

		public int getCutOffPage() {
			return this.mCutOffPage;
		}
	}
}

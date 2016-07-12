package com.tek.landlord.property;

import com.tek.landlord.R;

import android.content.Context;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AddTenantView extends LinearLayout{

	private TextView mName;
	private TextView mPhone;
	
	private RelativeLayout mNameAndDleteButtonLayout;
	private TextView mEmail;
	
	private Button mDeleteTenant;
	
	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	
	public AddTenantView(Context context, final String name,final String phone, final String email) {
		super(context);
		mName = new TextView(context);
		mName.setText(name);
		mEmail = new TextView(context);
		mEmail.setText(email);
		mPhone = new TextView(context);
		mPhone.setText(phone);
		createAndViewNameAndButtonView(context);
		this.addView(mPhone);
		this.addView(mEmail);
		this.params.setMargins(5, 2, 0, 0);
		this.setLayoutParams(params);
	}

	private void createAndViewNameAndButtonView(Context context) {
		mDeleteTenant = new Button(context);
		this.params.setMargins(5, 2, 0, 0);
		mNameAndDleteButtonLayout = new RelativeLayout(context);
		mNameAndDleteButtonLayout.setLayoutParams(params);
		mNameAndDleteButtonLayout.addView(mName);
		mNameAndDleteButtonLayout.addView(mDeleteTenant);
		mName.setPadding(5, 0, 5, 0);
		mDeleteTenant.setBackgroundDrawable(getResources().getDrawable(R.drawable.cancel));
		this.addView(mNameAndDleteButtonLayout);
	}

	public static AddTenantView aTenantView(Context context, final String name, final String phone, final String email) {
		AddTenantView addTenantView = new AddTenantView(context, name, phone, email);
		addTenantView.setOrientation(LinearLayout.VERTICAL);
		return addTenantView;
	}
}

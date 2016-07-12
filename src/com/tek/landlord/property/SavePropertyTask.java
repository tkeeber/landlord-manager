package com.tek.landlord.property;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.tek.landlord.dao.NewsfeedDao;
import com.tek.landlord.dao.PropertyDao;
import com.tek.landlord.domain.NewsfeedItem;
import com.tek.landlord.domain.NewsfeedItemBuilder;
import com.tek.landlord.domain.Property;

public class SavePropertyTask extends AsyncTask<Property, Void, String> {

    Context context;
    ProgressDialog waitSpinner;

    CloseActivityHandler mCloseHandler;

    public SavePropertyTask(Context context, CloseActivityHandler closeHandler) {
        this.context = context;
        this.mCloseHandler = closeHandler;
    }

    @Override
    protected String doInBackground(Property... models) {
        Property model = models[0];

        new PropertyDao(this.context).createProperty(model);

        NewsfeedItem item = NewsfeedItemBuilder.aNewPropertyNewsfeedItem(model.getName(), model.getAddress()).build();
        new NewsfeedDao(this.context).createNewsfeedItem(item);
        return "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.os.AsyncTask#onPreExecute()
     */
    @Override
    protected void onPreExecute() {
        this.waitSpinner = ProgressDialog.show(this.context, "Please Wait ...", "Saving new property", true);

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        this.waitSpinner.cancel();
        this.mCloseHandler.close();
    }

}

// 05-06 14:28:07.141: E/WindowManager(1087): android.view.WindowLeaked: Activity com.tek.landlord.MainActivity has leaked window com.android.internal.policy.impl.PhoneWindow$DecorView{b3e93d10 V.E..... R.....ID 0,0-480,243} that was originally added here

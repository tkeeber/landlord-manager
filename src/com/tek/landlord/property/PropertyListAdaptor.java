package com.tek.landlord.property;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.tek.landlord.R;
import com.tek.landlord.domain.NewsfeedType;
import com.tek.landlord.domain.NewsfeedItem.NewsfeedEntry;
import com.tek.landlord.domain.Property.PropertyEntry;
import com.tek.landlord.domain.PropertyType;

public class PropertyListAdaptor  extends CursorAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    public PropertyListAdaptor(Context context, Cursor c) {
        super(context, c);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context); 
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = this.mLayoutInflater.inflate(R.layout.property_row, parent, false);
        return v;
    }

    @Override
    public void bindView(View v, Context context, Cursor c) {
        String title = c.getString(c.getColumnIndexOrThrow(PropertyEntry.COLUMN_NAME));
        int type = c.getInt(c.getColumnIndexOrThrow(PropertyEntry.COLUMN_TYPE));
        String detail = c.getString(c.getColumnIndexOrThrow(PropertyEntry.COLUMN_ADDRESS));

        TextView tvTitle = (TextView) v.findViewById(R.id.property_name);
        if (tvTitle != null) {
        	tvTitle.setText(title);
        }

        TextView tvType = (TextView) v.findViewById(R.id.property_type);
        if (tvType != null) {
        	PropertyType propertyType = PropertyType.from(type);
        	tvType.setText(propertyType != null ? PropertyType.from(type).getDesciption() : "");
        }   
        
        TextView tvDetail = (TextView) v.findViewById(R.id.property_address);
        if (tvDetail != null) {
        	tvDetail.setText(detail);
        }
        
//        TextView tvType = (TextView) v.findViewById(R.id.type);
//        tvType.setText(NewsfeedType.from(type).getTitlePrefix());
//        ImageView del_image = (ImageView) v.findViewById(R.id.item_deletion);
//        del_image.setVisibility(ImageView.INVISIBLE);
//        if (deletion == 1) {
//            del_image.setVisibility(ImageView.VISIBLE);
//        }
    }

}

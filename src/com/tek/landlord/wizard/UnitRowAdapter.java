package com.tek.landlord.wizard;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tek.landlord.R;

public class UnitRowAdapter extends com.nhaarman.listviewanimations.ArrayAdapter<String> {

    private List<String> data;
    private LayoutInflater layoutInflator;
    private final ListRemoveCallback listRemoveCallback;

    public UnitRowAdapter(Context context, int textViewResourceId, List<String> data, ListRemoveCallback listRemoveCallback) {
        super(data);
        this.data = data;
        this.listRemoveCallback = listRemoveCallback;
        this.layoutInflator = LayoutInflater.from(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = this.layoutInflator.inflate(R.layout.wizard_framework_add_property_unit_row, parent, false);
        ImageButton deleteImageView = (ImageButton) row.findViewById(R.id.unit_remove_button);
        deleteImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                UnitRowAdapter.this.listRemoveCallback.remove(position);
            }
        });
        TextView unitName = (TextView) row.findViewById(R.id.unit_name);
        unitName.setText(this.data.get(position));
        return row;
    }
}

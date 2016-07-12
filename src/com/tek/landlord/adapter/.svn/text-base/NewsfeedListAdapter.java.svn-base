package com.tek.landlord.adapter;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.ArrayAdapter;
import com.tek.landlord.R;
import com.tek.landlord.domain.NewsfeedItem;
import com.tek.landlord.domain.NewsfeedItem.NewsfeedEntry;

public class NewsfeedListAdapter extends ArrayAdapter<NewsfeedItem> {

	private LayoutInflater mLayoutInflater;
	private NewsfeedAdaptor newsfeedAdaptor;

	public NewsfeedListAdapter(Context context, List<NewsfeedItem> newsfeedItems) {
		super(newsfeedItems);
		this.mLayoutInflater = LayoutInflater.from(context);
		this.newsfeedAdaptor = new NewsfeedAdaptor(context);
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = this.mLayoutInflater.inflate(R.layout.newsfeed_row, parent,
				false);

		NewsfeedItem newsfeedItem = get(position);
		String title = newsfeedItem.getTitle();
		String date = newsfeedItem.getDate();
		String detail = newsfeedItem.getDetail();
		int newsfeedItemType = newsfeedItem.getType();

		ImageView image = (ImageView) v.findViewById(R.id.newsfeed_icon);
		image.setBackgroundDrawable(this.newsfeedAdaptor
				.adaptToIcon(newsfeedItemType));

		TextView tvTitle = (TextView) v.findViewById(R.id.newsfeed_title);
		if (tvTitle != null) {
			tvTitle.setText(title);
		}

		TextView tvDate = (TextView) v.findViewById(R.id.newsfeed_date);
		if (tvDate != null) {
			tvDate.setText(date);
		}

		TextView tvDetail = (TextView) v.findViewById(R.id.newsfeed_detail);
		if (tvDetail != null) {
			tvDetail.setText(detail);
		}
		return v;
	}

	// @Override
	// public View newView(Context context, Cursor cursor, ViewGroup parent) {
	// View v = this.mLayoutInflater.inflate(R.layout.newsfeed_row, parent,
	// false);
	// return v;
	// }

//	@Override
//	public void bindView(View v, Context context, Cursor c) {
//		String title = c.getString(c
//				.getColumnIndexOrThrow(NewsfeedEntry.COLUMN_NAME_TITLE));
//		String date = c.getString(c
//				.getColumnIndexOrThrow(NewsfeedEntry.COLUMN_NAME_DATE));
//		String detail = c
//				.getString(c
//						.getColumnIndexOrThrow(NewsfeedEntry.COLUMN_NAME_DESCRIPTION_TEXT));
//		int newsfeedItemType = c.getInt(c
//				.getColumnIndexOrThrow(NewsfeedEntry.COLUMN_NAME_TYPE));
//		int isActioned = c.getInt(c
//				.getColumnIndexOrThrow(NewsfeedEntry.COLUMN_NAME_ACTIONED));
//
//		ImageView image = (ImageView) v.findViewById(R.id.newsfeed_icon);
//		image.setBackgroundDrawable(this.newsfeedAdaptor
//				.adaptToIcon(newsfeedItemType));
//
//		TextView tvTitle = (TextView) v.findViewById(R.id.newsfeed_title);
//		if (tvTitle != null) {
//			tvTitle.setText(title);
//		}
//
//		TextView tvDate = (TextView) v.findViewById(R.id.newsfeed_date);
//		if (tvDate != null) {
//			tvDate.setText(date);
//		}
//
//		TextView tvDetail = (TextView) v.findViewById(R.id.newsfeed_detail);
//		if (tvDetail != null) {
//			tvDetail.setText(detail);
//		}

		// TextView tvType = (TextView) v.findViewById(R.id.type);
		// tvType.setText(NewsfeedType.from(type).getTitlePrefix());
		// ImageView del_image = (ImageView) v.findViewById(R.id.item_deletion);
		// del_image.setVisibility(ImageView.INVISIBLE);
		// if (deletion == 1) {
		// del_image.setVisibility(ImageView.VISIBLE);
		// }
	//}
}

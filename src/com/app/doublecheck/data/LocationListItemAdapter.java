package com.app.doublecheck.data;

import com.app.doublecheck.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LocationListItemAdapter extends BaseAdapter{
	private final LocationListItem[] locationListItem;
	
	public LocationListItemAdapter(LocationListItem...locationListItem){
		this.locationListItem = locationListItem;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return locationListItem.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return locationListItem[position];
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewGroup item = getViewGroup(convertView, parent);
		TextView addr = (TextView)item.findViewById(R.id.lli_addr_textview);
		LocationListItem loc = locationListItem[position];
		addr.setText(loc.addressStr);
		
		return item;
	}
	
	private ViewGroup getViewGroup(View reuse, ViewGroup parent) { 
	    if(reuse instanceof ViewGroup) { 
	        return (ViewGroup)reuse;
	    }
	    Context context = parent.getContext(); 
	    LayoutInflater inflater = LayoutInflater.from(context); 
	    ViewGroup item = (ViewGroup)inflater.inflate( 
	            R.layout.location_list_item, null);
	 
	    return item; 
	}

}

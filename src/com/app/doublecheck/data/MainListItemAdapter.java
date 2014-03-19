package com.app.doublecheck.data;


import com.app.doublecheck.Constants;
import com.app.doublecheck.R;
import com.app.doublecheck.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainListItemAdapter extends BaseAdapter{
	private final MainListItem[] mainListItems;
	
	public MainListItemAdapter(MainListItem...mainListItems){
		this.mainListItems = mainListItems;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return mainListItems.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mainListItems[position];
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewGroup item = getViewGroup(convertView, parent);
		
		TextView type_cat = (TextView)item.findViewById(R.id.mli_type_cat_textview);
		TextView start = (TextView)item.findViewById(R.id.mli_start_textview);
		TextView end = (TextView)item.findViewById(R.id.mli_end_textview);
		
		MainListItem listItem =  mainListItems[position];
		String cat = "";
		String type = "";
		String startAddr = "";
		String endAddr = "";
		String startTime = "";
		String endTime = "";
		switch(listItem.type){
		case Constants.TYPE_NEED_RIDE:
			type = "I Need Ride!";break;
		case Constants.TYPE_OFFER_RIDE:
			type = "I Offer Ride!";break;
		case Constants.TYPE_COMMON:
			type = "My Schedule";break;
		}
		switch(listItem.category){
		case Constants.CAT_CARPOOL:
			cat = "Carpool";break;
		case Constants.CAT_TRAVEL:
			cat = "Travel";break;
		case Constants.CAT_TEMP:
			cat = "Temp";break;
		case Constants.CAT_COMMON:
			cat = "";
		}
		startTime = Util.dateToTimeStr(listItem.startDate);
		endTime = Util.dateToTimeStr(listItem.endDate);
		startAddr = listItem.startAddress;
		endAddr = listItem.endAddress;
		
		String firstLine = type + " " + "(" + cat + ")";
		String secondLine = startAddr + " " + startTime;
		String thirdLine = endAddr + " " + endTime;
		type_cat.setText(firstLine);
		start.setText(secondLine);
		end.setText(thirdLine);
		
		return item;
	}
	
	private ViewGroup getViewGroup(View reuse, ViewGroup parent) { 
	    if(reuse instanceof ViewGroup) { 
	        return (ViewGroup)reuse;
	    }
	    Context context = parent.getContext(); 
	    LayoutInflater inflater = LayoutInflater.from(context); 
	    ViewGroup item = (ViewGroup)inflater.inflate( 
	            R.layout.main_list_item, null);
	 
	    return item; 
	}

}

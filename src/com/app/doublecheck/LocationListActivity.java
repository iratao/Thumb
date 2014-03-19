package com.app.doublecheck;

import java.util.List;

import com.app.doublecheck.data.LocationListItem;
import com.app.doublecheck.data.LocationListItemAdapter;
import com.app.doublecheck.map.MapOverlayActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class LocationListActivity extends Activity {
	public static List<Address> addressList;
	private ListView list;
	private Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_list_activity);
		
		list = (ListView)findViewById(R.id.lla_listview);
		
		int size = addressList.size();
		LocationListItem[] items = new LocationListItem[size];
		for(int i = 0; i < size; i++){
			items[i] = new LocationListItem(
					Util.addressToStr(addressList.get(i)), addressList.get(i));
		}
		
		list.setAdapter(new LocationListItemAdapter(items));
		
		list.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Global.currentAddress = addressList.get(position);
				MapOverlayActivity.state = Constants.STATE_MAP_FROM_LOCATION_LIST;
				Intent intent = new Intent(context, MapOverlayActivity.class);
				startActivity(intent);
			}
			
		});

	}
	
	
	
	
}

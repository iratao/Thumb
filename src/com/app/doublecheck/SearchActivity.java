package com.app.doublecheck;

import java.util.ArrayList;
import java.util.List;

import com.app.doublecheck.data.Record;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

public class SearchActivity extends Activity{
	public static int state = Constants.STATE_SEARCH_INI;
	private Connector connector;
	private Context context = this;
	
	private TextView startAddressTextView = null;
	private TextView endAddressTextView = null;
	private RadioButton directPublishRadioButton;
	private RadioButton offerRideRadioButton;
	private RadioButton needRideRadioButton;
	private RadioButton carpoolRadioButton;
	private RadioButton travelRadioButton;
	private RadioButton tempRadioButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		connector = Global.connector;
		
		startAddressTextView = (TextView)findViewById(R.id.sa_startaddress_textview);
        endAddressTextView = (TextView)findViewById(R.id.sa_endaddress_textview);
        directPublishRadioButton = (RadioButton)findViewById(R.id.sa_direct_publish_button);
    	offerRideRadioButton = (RadioButton)findViewById(R.id.sa_offer_ride_button);
    	needRideRadioButton = (RadioButton)findViewById(R.id.sa_need_ride_button);
    	carpoolRadioButton = (RadioButton)findViewById(R.id.sa_carpool_button);
    	travelRadioButton = (RadioButton)findViewById(R.id.sa_travel_button);
    	tempRadioButton = (RadioButton)findViewById(R.id.sa_temp_button);
    	
    	setButtonListeners();

    	switch(state){
    	case Constants.STATE_SEARCH_START:
    		this.setStartAddress();break;
    	case Constants.STATE_SEARCH_END:
    		this.setEndAddress();break;
    	case Constants.STATE_SEARCH_BOTH:
    		this.setStartAddress();
    		this.setEndAddress();
    		break;
    	}
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.map_operaiton_menu_search, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//connector.search(Global.startAddress.getLongitude());
		SearchResultActivity.state = this.state;
		Intent intent = new Intent(context, SearchResultActivity.class);
		startActivity(intent);
		return true;
	}
	
	private void setStartAddress(){
    	startAddressTextView.setText(connector.getStartAddressStr());
    };
    
    private void setEndAddress(){
    	endAddressTextView.setText(connector.getEndAddressStr());
    }
    
    private void setButtonListeners(){
    	directPublishRadioButton.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Global.type = Constants.TYPE_COMMON;
				carpoolRadioButton.setClickable(false);
				travelRadioButton.setClickable(false);
				tempRadioButton.setClickable(false);
			}
    	});
    	
    	offerRideRadioButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Global.type = Constants.TYPE_OFFER_RIDE;
				carpoolRadioButton.setClickable(true);
				travelRadioButton.setClickable(true);
				tempRadioButton.setClickable(true);
			}
    		
    	});
    	
    	needRideRadioButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Global.type = Constants.TYPE_NEED_RIDE;
				carpoolRadioButton.setClickable(true);
				travelRadioButton.setClickable(true);
				tempRadioButton.setClickable(true);
			}
    		
    	});
    	
    	carpoolRadioButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Global.category = Constants.CAT_CARPOOL;
			}
    		
    	});
    	
    	travelRadioButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Global.category = Constants.CAT_TRAVEL;
			}
    		
    	});
    	
    	tempRadioButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Global.category = Constants.CAT_TEMP;
			}
    		
    	});
    }
	
	
	
	
	
}

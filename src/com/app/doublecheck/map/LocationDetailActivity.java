package com.app.doublecheck.map;

import com.app.doublecheck.Connector;
import com.app.doublecheck.Global;
import com.app.doublecheck.R;
import com.app.doublecheck.Util;
import com.app.doublecheck.R.id;
import com.app.doublecheck.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LocationDetailActivity extends Activity{
	
	private Connector connector;
	private Context context = this;

	public static LocationData locationData = null;
	
	private TextView locAddrTextView = null;
	private Button setStartButton = null;
	private Button setEndButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.locdetail_activity);
		
		connector = Global.connector;
		
		locAddrTextView = (TextView)findViewById(R.id.lda_addr_textview);
		setStartButton = (Button)findViewById(R.id.setstart_button);
		setEndButton = (Button)findViewById(R.id.setend_button);
		
		Address addr = locationData.address;
		String address = Util.addressToStr(addr);
		locAddrTextView.setText(address);
		
		setStartButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				connector.setStartAddress(locationData.address);
				Intent intent = new Intent(context, MapOverlayActivity.class);
				startActivity(intent);
			}
			
		});
		
		setEndButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				connector.setEndAddress(locationData.address);
				Intent intent = new Intent(context, MapOverlayActivity.class);
				startActivity(intent);
			}
			
		});
		
	}
	
	

}

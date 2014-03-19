package com.app.doublecheck;

import com.app.doublecheck.data.Record;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RecordDetailActivity extends Activity{
	private Connector connector;
	private Record record = null;
	
	private TextView type_cat;
	private TextView start;
	private TextView end;
	private TextView otherInfo;
	private Button callMeButton;
	private Button emailMeButton;
	
	private int userID;
	private String phoneNumber;
	private String email;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.records_detail_activity);
		
		connector = Global.connector;
		
		record = Global.currentRecord;
		
		type_cat = (TextView)findViewById(R.id.rda_type_cat_textview);
		start = (TextView)findViewById(R.id.rda_start_textview);
		end = (TextView)findViewById(R.id.rda_end_textview);
		otherInfo = (TextView)findViewById(R.id.rda_info_textview);
		callMeButton = (Button)findViewById(R.id.rda_callme_button);
		emailMeButton = (Button)findViewById(R.id.rda_mailme_button);
		
		initRecord();
		initUser();
		initButton();

	}
	
	private void initRecord(){
		String cat = "";
		String type = "";
		String startAddr = "";
		String endAddr = "";
		String startTime = "";
		String endTime = "";
		
		switch(record.type){
		case Constants.TYPE_NEED_RIDE:
			type = "I Need Ride!";break;
		case Constants.TYPE_OFFER_RIDE:
			type = "I Offer Ride!";break;
		case Constants.TYPE_COMMON:
			type = "My Schedule";break;
		}
		switch(record.category){
		case Constants.CAT_CARPOOL:
			cat = "Carpool";break;
		case Constants.CAT_TRAVEL:
			cat = "Travel";break;
		case Constants.CAT_TEMP:
			cat = "Temp";break;
		case Constants.CAT_COMMON:
			cat = "";
		}
		startTime = Util.dateToTimeStr(record.startDate);
		endTime = Util.dateToTimeStr(record.endDate);
		startAddr = record.startAddress;
		endAddr = record.endAddress;
		
		String firstLine = type + " " + "(" + cat + ")";
		String secondLine = startAddr + " " + startTime;
		String thirdLine = endAddr + " " + endTime;
		type_cat.setText(firstLine);
		start.setText(secondLine);
		end.setText(thirdLine);
		otherInfo.setText(record.other);
	}

	private void initUser(){
		userID = record.userID;
		phoneNumber = connector.getUserPhoneNumber(userID);
		email = connector.getUserEmail(userID);
	}
	
	private void initButton(){
		
		this.callMeButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
				startActivity(intent);
			}
		});
		
		this.emailMeButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
				emailIntent.setType("plain/text");
				startActivity(Intent.createChooser(emailIntent, "Send your email in:"));
			}
			
		});
	}

}

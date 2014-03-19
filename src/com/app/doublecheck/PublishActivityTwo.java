package com.app.doublecheck;

import com.app.doublecheck.map.MapOverlayActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class PublishActivityTwo extends Activity{
	private Context context = this;
	private Connector connector;
	
	private RadioGroup mRadioGroup;
	private Button publishButton;
	private RadioButton directPublishButton;
	private RadioButton offerRideButton;
	private RadioButton needRideButton;
	private EditText infoEditText;
	
	private static final int DIRECT_PUBLISH = 0;
	private static final int OFFER_RIDE = 1;
	private static final int NEED_RIDE = 2;
	private static final int INITIAL = -1;
	private static int state = INITIAL;
	
	private static final int INFO_DIALOG_ID = 1;
	private static final int RIDE_TYPE_DIALOG_ID = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish_activity_two);
		
		connector = Global.connector;
		
		publishButton = (Button)findViewById(R.id.pa_publish_button);
		mRadioGroup = (RadioGroup)findViewById(R.id.pa_radiogroup);
		directPublishButton = (RadioButton)findViewById(R.id.pa_direct_publish_button);
		offerRideButton = (RadioButton)findViewById(R.id.pa_offer_ride_button);
		needRideButton = (RadioButton)findViewById(R.id.pa_need_ride_button);
		infoEditText = (EditText)findViewById(R.id.pa_info_edittext);
		
		
		publishButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				if(state == INITIAL){
					showDialog(INFO_DIALOG_ID);
				} else{
					publishCheck();
				}
			}
			
		});
		
		directPublishButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				state = DIRECT_PUBLISH;
				System.out.println("Checked.");
			}
			
		});
		offerRideButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				state = OFFER_RIDE;
				System.out.println("Checked.");
				//publish();
			}
			
		});
		needRideButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				state = NEED_RIDE;
				System.out.println("Checked.");
				//publish();
			}
			
		});

	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id){
		case INFO_DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Please Choose A Category.")
			       .setCancelable(false).setNeutralButton("OK", new DialogInterface.OnClickListener(){
			    	   public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();}});
			AlertDialog alert = builder.create();
			return alert;
		case RIDE_TYPE_DIALOG_ID:
			final String[] items = {"Carpool", "Travel", "Temp"};
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setTitle("Choose a Category");
			builder2.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
				switch(which){
				case 0:
		    		Global.category = Constants.CAT_CARPOOL;publish();break;
		    	case 1:
		    		Global.category = Constants.CAT_TRAVEL;publish();break;
		    	case 2:
		    		Global.category = Constants.CAT_TEMP;publish();break;
				}
				}
			});
			
			AlertDialog alert2 = builder2.create();
			return alert2;
		}
		return null;
	}
	
		
		



	private void publishCheck(){
		switch(state){
		case DIRECT_PUBLISH:
			Global.type = Constants.TYPE_COMMON;
			Global.category = Constants.CAT_COMMON;
			publish();
			break;
		case NEED_RIDE:
			Global.type = Constants.TYPE_NEED_RIDE;
			showDialog(RIDE_TYPE_DIALOG_ID);
			break;
		case OFFER_RIDE:
			Global.type = Constants.TYPE_OFFER_RIDE;
			showDialog(RIDE_TYPE_DIALOG_ID);
			break;
		}
	}
	
	private void publish(){
		Global.otherInfo = this.infoEditText.getText().toString();
		String temp = connector.publishRecord();
		System.out.println("Publish Activity Two: " + temp);
		Intent intent;
		intent = new Intent(context, MainActivity.class);
		this.startActivity(intent);
	}
	
	
	
	
}

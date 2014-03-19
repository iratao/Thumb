package com.app.doublecheck;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class PublishActivity extends Activity {
	private Connector connector;
	private Context context = this;
	
	private TextView startAddressTextView = null;
	private TextView endAddressTextView = null;
	
	private Button startTimeButton = null;
	private Button endTimeButton = null;
	private Button startDateButton = null;
	private Button endDateButton = null;
	private Button nextButton = null;
	
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;
	private SimpleDateFormat dateFormat;
	private SimpleDateFormat timeFormat;
	
	private static final int START_TIME_DIALOG_ID = 0;
	private static final int START_DATE_DIALOG_ID = 1;
	private static final int END_TIME_DIALOG_ID = 2;
	private static final int END_DATE_DIALOG_ID = 3;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_activity);
        
        connector = Global.connector;
        
        startAddressTextView = (TextView)findViewById(R.id.pa_startaddress_textview);
        endAddressTextView = (TextView)findViewById(R.id.pa_endaddress_textview);
        
        startTimeButton = (Button)findViewById(R.id.pa_choose_starttime_button);
        startDateButton = (Button)findViewById(R.id.pa_choose_startdate_button);
        endTimeButton = (Button)findViewById(R.id.pa_choose_endtime_button);
        endDateButton = (Button)findViewById(R.id.pa_choose_enddate_button);
        nextButton = (Button)findViewById(R.id.pa_nextstep_button);
        
        dateFormat = new SimpleDateFormat(startDateButton.getText().toString());
        timeFormat = new SimpleDateFormat(startTimeButton.getText().toString());
        
        final Calendar calendar = Calendar.getInstance();
        
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        
        final Date currentDate = calendar.getTime();
       
        startTimeButton.setText(timeFormat.format(currentDate));
        startDateButton.setText(dateFormat.format(currentDate));
        endTimeButton.setText(timeFormat.format(currentDate));
        endDateButton.setText(dateFormat.format(currentDate));
        
        this.setStartAddress();
        this.setEndAddress();
        
        nextButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				setTime();
				Intent intent = new Intent(context, PublishActivityTwo.class);
				startActivity(intent);
			}
        });
        
        startTimeButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				showDialog(START_TIME_DIALOG_ID);
			}
        	
        });
        
        startDateButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				showDialog(START_DATE_DIALOG_ID);
			}
        	
        });
        
        endTimeButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				showDialog(END_TIME_DIALOG_ID);
			}
        	
        });
        
        endDateButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				showDialog(END_DATE_DIALOG_ID);
			}
        	
        });
        
    }
    
    private void setTime(){
    	Date startDate = Util.strToDate(startDateButton.getText() + " " + startTimeButton.getText());
    	Date endDate = Util.strToDate(endDateButton.getText() + " " + endTimeButton.getText());
    	connector.setStartDate(startDate);
    	connector.setEndDate(endDate);
    }
    
    private void setStartAddress(){
    	// TODO
    	//Address a = connector.getStartAddress();
    	//String addrStr = Util.addressToStr(a);
    	//startAddressTextView.setText(addrStr);
    	//startAddressTextView.setText(Util.testAddress);
    	startAddressTextView.setText(connector.getStartAddressStr());
    };
    
    private void setEndAddress(){
    	//Address a = connector.getEndAddress();
    	//String addrStr = Util.addressToStr(a);
    	//endAddressTextView.setText(addrStr);
    	//endAddressTextView.setText(Util.testAddress);
    	endAddressTextView.setText(connector.getEndAddressStr());
    }
    
    private Calendar parseCalendar(
            final CharSequence text,
            final SimpleDateFormat format) {

        try {
            final Date parsedDate = format.parse(text.toString());
            final Calendar calendar = Calendar.getInstance();

            calendar.setTime(parsedDate);

            return calendar;
        } catch(final ParseException pe) {
            Log.e("DateParse", "Couldn't parse date: " + text, pe);
            throw new RuntimeException(pe);
        }
    }
    
    @Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
    	
    	if(id == START_DATE_DIALOG_ID || id == START_TIME_DIALOG_ID
    			|| id == END_DATE_DIALOG_ID || id == END_TIME_DIALOG_ID){
    		final Calendar calendar = parseCalendar(
                    startDateButton.getText(),
                    dateFormat);
    	    
    	    switch(id){
        	
        	case START_TIME_DIALOG_ID:
        		return new TimePickerDialog(this,
    					this.mStartTimeSetListener, 
    					calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), false);
    		case START_DATE_DIALOG_ID:
    			return new DatePickerDialog(this, this.mStartDateSetListener,
    					calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
        	case END_TIME_DIALOG_ID:
        		return new TimePickerDialog(this,
    					this.mEndTimeSetListener, 
    					calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), false);
    		case END_DATE_DIALOG_ID:
        		return new DatePickerDialog(this, this.mEndDateSetListener,
        				calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
        	}
    	}
    	
		return null;
	}



	private DatePickerDialog.OnDateSetListener mStartDateSetListener = 
		new DatePickerDialog.OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				mYear = year;
				mMonth = monthOfYear;
				mDay = dayOfMonth;
			    updateStartDate();
			}
	};
	
	private DatePickerDialog.OnDateSetListener mEndDateSetListener = 
		new DatePickerDialog.OnDateSetListener() {
			
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				mYear = year;
				mMonth = monthOfYear;
				mDay = dayOfMonth;
			    updateEndDate();
			}
	};
	
	private TimePickerDialog.OnTimeSetListener mStartTimeSetListener =
		new TimePickerDialog.OnTimeSetListener() {
			
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				mHour = hourOfDay;
				mMinute = minute;
				updateStartTime();
			}
	};
	
	private TimePickerDialog.OnTimeSetListener mEndTimeSetListener = 
		new TimePickerDialog.OnTimeSetListener() {
			
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				mHour = hourOfDay;
				mMinute = minute;
				updateEndTime();
			}
	};
	
    private void updateStartDate() {
		
		final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mYear);
        calendar.set(Calendar.MONTH, mMonth);
        calendar.set(Calendar.DAY_OF_MONTH, mDay);
        
        this.startDateButton.setText(dateFormat.format(calendar.getTime()));
	}
    
    private void updateEndDate(){
    	final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mYear);
        calendar.set(Calendar.MONTH, mMonth);
        calendar.set(Calendar.DAY_OF_MONTH, mDay);
        
        this.endDateButton.setText(dateFormat.format(calendar.getTime()));
    }
    
    private void updateStartTime(){
    	
    	final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, mHour);
        calendar.set(Calendar.MINUTE, mMinute);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        
        startTimeButton.setText(timeFormat.format(calendar.getTime()));
    }
    
    private void updateEndTime(){
    	
    	final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, mHour);
        calendar.set(Calendar.MINUTE, mMinute);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        
        endTimeButton.setText(timeFormat.format(calendar.getTime()));
    }
    
    
    
    
    
    
    
}
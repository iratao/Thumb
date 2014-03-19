package com.app.doublecheck;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.doublecheck.data.MainListItem;
import com.app.doublecheck.data.MainListItemAdapter;
import com.app.doublecheck.data.MainSeperatedListAdapter;
import com.app.doublecheck.data.Record;
import com.app.doublecheck.map.MapOverlayActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	private Connector connector;
	
	private Context context = this;
	
	private EditText inputEditText = null;
	private Button okButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_activity);
		connector = new Connector();
		Global.connector = connector;
		
		inputEditText = (EditText)findViewById(R.id.ma_input_destination_input_edittext);
		okButton = (Button)findViewById(R.id.ma_input_destination_input_button);
		
		okButton.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String input = null;
				input = inputEditText.getText().toString();
				if(input != null){
					Global.mainInput = input;
				    MapOverlayActivity.state = Constants.STATE_MAP_FROM_MAIN;
					Intent intent = new Intent(context, MapOverlayActivity.class);
					startActivity(intent);
				}else{
					MapOverlayActivity.state = Constants.STATE_MAP_INI;
					Intent intent = new Intent(context, MapOverlayActivity.class);
					startActivity(intent);
				}
				
			}
			
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initiateList();
		clearGlobal();
	}
	
	


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initiateList();
		clearGlobal();
	}

	private MainListItem[] createItems(Date date, int startIndex, List<Record> records){
		List<MainListItem> items = new ArrayList<MainListItem>();
		for(int i = startIndex; i < records.size(); i++){
			Record record = records.get(i);
			Date newDate = record.startDate;
			
			if(Util.compareDateDay(date, newDate)!=0)
				break;
			items.add(new MainListItem(record.startAddress, record.endAddress, record.category, 
					record.type, record.startDate, record.endDate));
		}
		
		MainListItem[] itemsArray = new MainListItem[items.size()];
		for(int i = 0; i < itemsArray.length; i++){
			itemsArray[i] = items.get(i);
		}
		
		return itemsArray;
	}
	
	private void initiateList(){
		final List<Record> records = connector.getLatestRecord(0, 5);
		List<String> headers = new ArrayList<String>();
		List<MainListItemAdapter> adapters = new ArrayList<MainListItemAdapter>();
		//List<MainListItem> items = new ArrayList<MainListItem>();
		
        MainSeperatedListAdapter sAdapter = new MainSeperatedListAdapter(this);
        
        for(int i = records.size()-1; i >= 0; ){
        	Date d =  (records.get(i)).startDate;
            headers.add(Util.dateToDateStr(d));
            MainListItem[] items = (MainListItem[])createItems(d, i, records);
            adapters.add(new MainListItemAdapter(items));
            i-=items.length;
        }
		
        /**
        MainListItemAdapter adapter1 = new MainListItemAdapter(
				new MainListItem("Start Address", "End Address", Constants.CAT_CARPOOL, 
					Constants.TYPE_NEED_RIDE, new Date(), new Date()),
				new MainListItem("Start Address", "End Address", Constants.CAT_CARPOOL, 
					Constants.TYPE_NEED_RIDE, new Date(), new Date()),
				new MainListItem("Start Address", "End Address", Constants.CAT_CARPOOL, 
					Constants.TYPE_NEED_RIDE, new Date(), new Date()));
		MainListItemAdapter adapter2 = new MainListItemAdapter(
				new MainListItem("Start Address", "End Address", Constants.CAT_CARPOOL, 
					Constants.TYPE_NEED_RIDE, new Date(), new Date()),
				new MainListItem("Start Address", "End Address", Constants.CAT_CARPOOL, 
					Constants.TYPE_NEED_RIDE, new Date(), new Date()),
				new MainListItem("Start Address", "End Address", Constants.CAT_CARPOOL, 
					Constants.TYPE_NEED_RIDE, new Date(), new Date()));
		
		sAdapter.addSection("May 11, 2011", adapter1);
		sAdapter.addSection("May 10, 2011", adapter2);
		*/
        for(int i = 0; i < headers.size(); i++){
        	sAdapter.addSection(headers.get(i), adapters.get(i));
        }
        
		ListView list = (ListView)findViewById(R.id.ma_listview);
		list.setAdapter(sAdapter);
		list.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Global.currentRecord = records.get(position);
				Intent intent = new Intent(context, RecordDetailActivity.class);
				startActivity(intent);
			}
			
		});
	}
	
	private void clearGlobal(){
		Global.startAddress = null;
		Global.endAddress = null;
		Global.startLocation = null;
		Global.endLocation = null;
		Global.startDate = null;
		Global.endDate = null;
		Global.type = -1;
		Global.category = -1;
		Global.otherInfo = "";

		Global.mainInput = null;
		Global.currentRecord = null;
	}
	
	

}

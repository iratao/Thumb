package com.app.doublecheck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.doublecheck.data.MainListItem;
import com.app.doublecheck.data.MainListItemAdapter;
import com.app.doublecheck.data.MainSeperatedListAdapter;
import com.app.doublecheck.data.Record;
import com.app.doublecheck.map.MapOverlayActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SearchResultActivity extends Activity{
	public static int state = Constants.STATE_SEARCH_INI;
	private Connector connector;
	private Context context = this;
	private List<Record> records; 
	
	private ProgressDialog progressDialog;
	
	private static final int MESSAGE_FIND_RECORDS = 1;
	
	private final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if(progressDialog != null) {
				progressDialog.dismiss();
			}
			
			switch(msg.what) {
			case MESSAGE_FIND_RECORDS:
				if(!records.isEmpty())
					initiateList();
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result_activity);
		connector = Global.connector;
		search();
		//initiateList();
		
	}
	
	private void search(){
		this.progressDialog = ProgressDialog.show(SearchResultActivity.this, 
				"Processing...", "Searching ...", true, false);
		Thread thread = new Thread()
        {
            public void run()
            {
                switch(state){
				case Constants.STATE_SEARCH_START:
					records = connector.searchByStartPoint(Global.startAddress.getLatitude(), Global.startAddress.getLongitude());
					handler.sendEmptyMessage(MESSAGE_FIND_RECORDS);
					break;
				case Constants.STATE_SEARCH_END:
					records = connector.searchByEndPoint(Global.endAddress.getLatitude(), Global.endAddress.getLongitude());
					handler.sendEmptyMessage(MESSAGE_FIND_RECORDS);
					break;
				case Constants.STATE_SEARCH_BOTH:
					records = connector.searchByStartEndPoint(Global.startAddress.getLatitude(), Global.startAddress.getLongitude(), 
							Global.endAddress.getLatitude(), Global.endAddress.getLongitude());
					handler.sendEmptyMessage(MESSAGE_FIND_RECORDS);
					break;
				}
            }
        };
        thread.start();
	}
	
	private void initiateList(){
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
        
        for(int i = 0; i < headers.size(); i++){
        	sAdapter.addSection(headers.get(i), adapters.get(i));
        }
        
		ListView list = (ListView)findViewById(R.id.sra_listview);
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

}

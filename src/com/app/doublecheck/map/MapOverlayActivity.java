package com.app.doublecheck.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.doublecheck.Constants;
import com.app.doublecheck.Global;
import com.app.doublecheck.LocationListActivity;
import com.app.doublecheck.PublishActivity;
import com.app.doublecheck.R;
import com.app.doublecheck.SearchActivity;
import com.app.doublecheck.R.drawable;
import com.app.doublecheck.R.id;
import com.app.doublecheck.R.layout;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;

public class MapOverlayActivity extends MapActivity {
	
	private LocationService locationService;
	private Context context = this; 
	
	public static int state = Constants.STATE_MAP_INI;
	
	private Button searchButton;
    private Button myLocationButton;
    private EditText searchInput;
    private MapView mapView;
    private Button listButton;
    
    private List<Address> addressList;
    private ArrayList<LocationOverlayItem> locations = null;
    private ArrayList<LocationOverlayItem> selocations = null;
    private SearchedLocations searchedLocationOverlay;
    private Geocoder geocoder;
    private Drawable marker;
    
    private ProgressDialog progressDialog;
    
    private static final int MESSAGE_FIND_LOCS = 1;
	private static final int MESSAGE_NOT_FIND_LOCS = 2;
	private static final int MESSAGE_SHOW_ON_MAP = 3;
	
	private static final int CHECK_OK = 1;
	private static final int CHECK_NEED_START = 2;
	private static final int CHECK_NEED_END = 3;
	private static final int CHECK_NEED_BOTH = 4;
	
	
    private final Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(progressDialog != null) {
				progressDialog.dismiss();
			}
			switch(msg.what) {
			case MESSAGE_FIND_LOCS:
				showLocations();
				
				break;
			case MESSAGE_NOT_FIND_LOCS:
				//Log.i(Constants.LOGTAG, " " + MapOverlayActivity.CLASSTAG
				//		+ " handleMessage - not find locations");
				Toast.makeText(context,
		                "Thumb cannot find this location. Please provide a more specific address.(Like: Country, State, Street)",
		                Toast.LENGTH_LONG).show();
				break;
			case MESSAGE_SHOW_ON_MAP:
				if (mapView.getOverlays().contains(searchedLocationOverlay)) {
	                mapView.getOverlays().remove(searchedLocationOverlay);
	            }
				searchedLocationOverlay= new SearchedLocations(marker, selocations, context);
				mapView.getOverlays().add(searchedLocationOverlay);
				GeoPoint pt2 = searchedLocationOverlay.getCenter();    // get the first-ranked point
				//Log.d(Constants.LOGTAG, " " + MapOverlayActivity.CLASSTAG
				//		+ " handleMessage - ps.isnull? " + (pt2 == null));
		        mapView.getController().setCenter(pt2);
		        mapView.getController().setZoom(15); 
		        //Log.d(Constants.LOGTAG, " " + MapOverlayActivity.CLASSTAG
	            //    			+ " handleMessage - recentered");
				mapView.invalidate();
				break;
				
			default:
				break;
			}
		}
    	
    };
	
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.mapview);
		
		mapView = (MapView) findViewById(R.id.map_view);
		searchButton = (Button)findViewById(R.id.search_button);
	    myLocationButton = (Button)findViewById(R.id.my_location_button);
	    searchInput = (EditText)findViewById(R.id.search_input);

        marker=getResources().getDrawable(R.drawable.bowling); 
        marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());
        locations = new ArrayList<LocationOverlayItem>();
        geocoder = new Geocoder(this);
        
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationService = new LocationService(lm);
        
        mapView.setBuiltInZoomControls(true);
        mapView.setClickable(true);
        
        LinearLayout listLayout = (LinearLayout)findViewById(R.id.list);  
        listButton = new Button(this);
        listButton.setText("More");
        listLayout.addView(listButton,
        		new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, 
                        LayoutParams.WRAP_CONTENT));
        listButton.setVisibility(View.INVISIBLE);
        
        searchButton.setOnClickListener(new OnClickListener() {

			public void onClick(final View v) {
				if(!searchInput.getText().toString().equals("")){
					getSearchedLocations(searchInput.getText().toString());
				}
			}
        });
        
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		switch(state){
		case Constants.STATE_MAP_INI:
			break;
		case Constants.STATE_MAP_FROM_MAIN:
			searchInput.setText(Global.mainInput);
			getSearchedLocations(Global.mainInput);break;
		case Constants.STATE_MAP_FROM_LOCATION_LIST:
			getSearchedLocation(Global.currentAddress);break;
		}
	}
	
	private void showLocations(){
		if (mapView.getOverlays().contains(searchedLocationOverlay)) {
            mapView.getOverlays().remove(searchedLocationOverlay);
        }
		
		searchedLocationOverlay= new SearchedLocations(marker, locations, context);
		//TestLocations testOverlay = new TestLocations(marker);
		mapView.getOverlays().add(searchedLocationOverlay);
		
		GeoPoint pt = searchedLocationOverlay.getCenter();    // get the first-ranked point
		//Log.d(Constants.LOGTAG, " " + MapOverlayActivity.CLASSTAG
		//		+ " handleMessage - ps.isnull? " + (pt == null));
        mapView.getController().setCenter(pt);
        mapView.getController().setZoom(15); 
        //Log.d(Constants.LOGTAG, " " + MapOverlayActivity.CLASSTAG
        //    			+ " handleMessage - recentered");
		
		listButton.setVisibility(View.VISIBLE);
		listButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				LocationListActivity.addressList = addressList;
				Intent intent = new Intent(context, LocationListActivity.class);
				startActivity(intent);
			}
			
		});
		
		mapView.invalidate();
		
	}
	
	private void getSearchedLocation(Address address){
		List<Address> list = new ArrayList<Address>();
		list.add(address);
		addressList = list;
		ArrayList<LocationData> locationDataList = getLocationDataList(addressList);
    	locations = getLocationOverlayItems(locationDataList);
    	handler.sendEmptyMessage(MESSAGE_FIND_LOCS);
	}
	
	private void getSearchedLocations(final String address) {
		this.progressDialog = ProgressDialog.show(MapOverlayActivity.this, 
				"Processing...", "Finding Location...", true, false);
		Thread thread = new Thread()
        {
            public void run()
            {
                try {
                	addressList = geocoder.getFromLocationName(address, 5);
                	
                	//Log.d(Constants.LOGTAG, " " + MapOverlayActivity.CLASSTAG
                	//		+ " getSearchedLocations - addressList.size() = " + addressList.size());
                	if(!addressList.isEmpty()){
                    	ArrayList<LocationData> locationDataList = getLocationDataList(addressList);
                    	locations = getLocationOverlayItems(locationDataList);
                    	handler.sendEmptyMessage(MESSAGE_FIND_LOCS);
                	} else {
                		handler.sendEmptyMessage(MESSAGE_NOT_FIND_LOCS);
                	}
                	
                }catch (IOException e) {
                    //Log.e(Constants.LOGTAG, " " + MapOverlayActivity.CLASSTAG 
                    //		+ " getSearchedLocations\n" + e.getMessage());
                }
            }
        };
        thread.start();
	}
	
	private ArrayList<LocationOverlayItem> getLocationOverlayItems(ArrayList<LocationData> locations) {
		ArrayList<LocationOverlayItem> list = new ArrayList<LocationOverlayItem>();
		for(LocationData ld : locations){
			LocationOverlayItem loi = new LocationOverlayItem(ld.gp, ld);
			list.add(loi);
		}
		return list;
	}
	
	private ArrayList<LocationData> getLocationDataList(List<Address> address) {
		ArrayList<LocationData> list = new ArrayList<LocationData>();
		for(Address ad : address) {
			LocationData data = new LocationData();
			data.address = ad;
			data.gp = LocationHelper.getGeoPoint(ad);
			data.featureName = ad.getFeatureName();
			data.title =  ad.getThoroughfare();
			list.add(data);
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.map_opetion_menu, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
	    case R.id.menu_publish:
	    	int check = check();
	    	if(check == this.CHECK_OK){
	    		Intent intent = new Intent(context, PublishActivity.class);
		        startActivity(intent);
	    	}else if(check == this.CHECK_NEED_START){
	    		Toast.makeText(context,
		                "Please set startpoint",
		                Toast.LENGTH_LONG).show();
	    	}else if(check == this.CHECK_NEED_END){
	    		Toast.makeText(context,
		                "Please set endpoint",
		                Toast.LENGTH_LONG).show();
	    		
	    	}else if(check == this.CHECK_NEED_BOTH){
	    		Toast.makeText(context,
		                "Please set startpoint and endpoint",
		                Toast.LENGTH_LONG).show();
	    	}
	        return true;
	    case R.id.menu_search:
	    	int checkSearch = check();
	    	if(checkSearch == this.CHECK_NEED_START){
	    		SearchActivity.state = Constants.STATE_SEARCH_END;
	    		Intent intent = new Intent(context, SearchActivity.class);
	    		startActivity(intent);
	    	}else if(checkSearch == this.CHECK_NEED_END){
	    		SearchActivity.state = Constants.STATE_SEARCH_START;
	    		Intent intent = new Intent(context, SearchActivity.class);
	    		startActivity(intent);
	    	}else if(checkSearch == this.CHECK_OK){
	    		SearchActivity.state = Constants.STATE_SEARCH_BOTH;
	    		Intent intent = new Intent(context, SearchActivity.class);
	    		startActivity(intent);
	    	}else if(checkSearch == this.CHECK_NEED_BOTH){
	    		Toast.makeText(context,
		                "Please set at least one address to search.",
		                Toast.LENGTH_LONG).show();
	    	}
	    	
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	private int check(){
		if(Global.startAddress == null && Global.endAddress == null){
			return this.CHECK_NEED_BOTH;
		}else if(Global.startAddress == null){
			return this.CHECK_NEED_START;
		}else if(Global.endAddress == null){
			return this.CHECK_NEED_END;
		}else{
			return this.CHECK_OK;
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}

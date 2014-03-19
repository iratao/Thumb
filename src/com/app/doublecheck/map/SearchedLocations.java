package com.app.doublecheck.map;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;

public class SearchedLocations extends ItemizedOverlay<LocationOverlayItem> {
	private ArrayList<LocationOverlayItem> locations = new ArrayList<LocationOverlayItem>();
    private Drawable marker;
    private Context context;

	public SearchedLocations(Drawable defaultMarker, ArrayList<LocationOverlayItem> locations, Context context) {
		super(defaultMarker);
		// TODO Auto-generated constructor stub
        this.marker = defaultMarker;
        this.context = context;
        for(LocationOverlayItem l : locations) {
        	this.locations.add(l);
        }
        populate();                  
	}
	
	

	@Override
	protected boolean onTap(final int index) {
		// TODO Auto-generated method stub
		final LocationData ld = this.locations.get(index).locationData;
		Intent intent = new Intent(context, LocationDetailActivity.class);
		LocationDetailActivity.locationData = ld;
		context.startActivity(intent);
		return true;
	}



	@Override
	protected LocationOverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return locations.get(i);
	}
	
	@Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		boundCenterBottom(marker);
    }

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return locations.size();
	}
	
}
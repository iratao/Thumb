package com.app.doublecheck.map;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class LocationOverlayItem extends OverlayItem{
	
	public final GeoPoint point;
	public final LocationData locationData;

	public LocationOverlayItem(GeoPoint point, LocationData ld) {
		super(point, ld.title, ld.featureName);
		this.point = point;
		this.locationData = ld;
		
	}

}

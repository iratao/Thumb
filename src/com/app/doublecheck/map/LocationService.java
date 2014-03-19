package com.app.doublecheck.map;

import com.google.android.maps.GeoPoint;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

public class LocationService {
	
	private LocationManager locationManager;
	
	public LocationService(LocationManager lm){
		this.locationManager = lm;
	}
	
	public GeoPoint getLastKnownPoint() {
		GeoPoint lastKnownPoint = null;
		
        Location lastKnownLocation = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastKnownLocation == null){
        	lastKnownLocation = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (lastKnownLocation != null) {
            lastKnownPoint = LocationHelper.getGeoPointByLocation(lastKnownLocation);            
        } else {
        	lastKnownPoint = null;
        }
        return lastKnownPoint;
	}

}

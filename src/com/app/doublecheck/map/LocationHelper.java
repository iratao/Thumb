package com.app.doublecheck.map;

import java.text.DecimalFormat;

import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;

public class LocationHelper {
	public static final String CLASSTAG = LocationHelper.class.getSimpleName();
    public static final double MILLION = 1e6;
    private static final DecimalFormat DEC_FORMAT = new DecimalFormat("###.##");
    
    public static final GeoPoint NANJING_DAXUE = new GeoPoint((int) (32.06 * LocationHelper.MILLION),
            (int) (118.78 * LocationHelper.MILLION));
    
    public static GeoPoint getGeoPoint(final Address loc) {
        int lat = (int) (loc.getLatitude() * LocationHelper.MILLION);
        int lon = (int) (loc.getLongitude() * LocationHelper.MILLION);
        return new GeoPoint(lat, lon);
    }
    
    public static GeoPoint getGeoPointByLocation(final Location loc) {
        int lat = (int) (loc.getLatitude() * LocationHelper.MILLION);
        int lon = (int) (loc.getLongitude() * LocationHelper.MILLION);
        return new GeoPoint(lat, lon);
    }
    
    

}

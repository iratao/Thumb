package com.app.doublecheck.data;

import android.location.Address;

public class LocationListItem {
	final String addressStr;
	final Address address;
	public LocationListItem(String addressStr, Address address){
		this.addressStr = addressStr;
		this.address = address;
	}

}

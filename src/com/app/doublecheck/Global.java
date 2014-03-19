package com.app.doublecheck;

import java.util.Date;

import com.app.doublecheck.data.Record;

import android.location.Address;
import android.location.Location;

public class Global {
	
	public static Address startAddress = null;
	public static Address endAddress = null;
	public static Location startLocation = null;
	public static Location endLocation = null;
	public static Date startDate = null;
	public static Date endDate = null;
	public static int type = -1;
	public static int category = -1;
	public static String otherInfo = "";
	public static int userID = 0;
	public static String userName = "";
	
	
	public static String mainInput = null;
	public static Record currentRecord = null;
	public static Address currentAddress = null;
	
	public static Connector connector = null;

}

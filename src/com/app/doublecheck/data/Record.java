package com.app.doublecheck.data;

import java.util.Date;

public class Record {
	public final int recordID;
	public final int userID;
	public final String userName;
	
	public final String startAddress;
	public final String endAddress;
	public final Date startDate;
	public final Date endDate;
	public final Double startLat;
	public final Double startLon;
	public final Double endLat;
	public final Double endLon;
	public final String other;
	public final int type;
	public final int category;
	
	public Record(int recordID, int userID, String userName, String startAddress, String endAddress, Date startDate, Date endDate, 
			Double startLat, Double startLon, Double endLat, Double endLon, String other, int type, int category){
		this.recordID = recordID;
		this.userID = userID;
		this.userName = userName;
		
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startLat = startLat;
		this.startLon = startLon;
		this.endLat = endLat;
		this.endLon = endLon;
		this.other = other;
		this.type = type;
		this.category = category;
	}
}

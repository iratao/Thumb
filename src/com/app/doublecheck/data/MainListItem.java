package com.app.doublecheck.data;

import java.util.Date;

public class MainListItem {
	final String startAddress;
	final String endAddress;
	final int category;
	final int type;
	final Date startDate;
	final Date endDate;
	
	public MainListItem(String startAddress, String endAddress, int category, 
			int type, Date startDate, Date endDate){
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.category = category;
		this.type = type;
		this.startDate = startDate;	
		this.endDate = endDate;
	}
	
	
	

}

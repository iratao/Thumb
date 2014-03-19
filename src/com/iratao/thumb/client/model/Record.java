package com.iratao.thumb.client.model;

import java.util.Date;

public class Record {
	// category的数字表示
	public static final int CAT_CARPOOL = 1;
	public static final int CAT_TRAVEL = 2;
	public static final int CAT_TEMP = 3;
	public static final int CAT_COMMON = 4;

	// type的数字表示
	public static final int TYPE_COMMON = 1;
	public static final int TYPE_NEED_RIDE = 2;
	public static final int TYPE_OFFER_RIDE = 3;

	public Integer recordID;
	public Integer userID;
	public String userName;

	public String startAddress;
	public String endAddress;
	public Date startDate;
	public Date endDate;
	public Double startLat;
	public Double startLon;
	public Double endLat;
	public Double endLon;
	public String other;
	public Integer type;
	public Integer category;

	public Record() {
	}

	public Record(Integer recordID, Integer userID, String userName,
			String startAddress, String endAddress, Date startDate,
			Date endDate, Double startLat, Double startLon, Double endLat,
			Double endLon, String other, Integer type, Integer category) {
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

	public Integer getRecordID() {
		return recordID;
	}

	public void setRecordID(Integer recordID) {
		this.recordID = recordID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getStartLat() {
		return startLat;
	}

	public void setStartLat(Double startLat) {
		this.startLat = startLat;
	}

	public Double getStartLon() {
		return startLon;
	}

	public void setStartLon(Double startLon) {
		this.startLon = startLon;
	}

	public Double getEndLat() {
		return endLat;
	}

	public void setEndLat(Double endLat) {
		this.endLat = endLat;
	}

	public Double getEndLon() {
		return endLon;
	}

	public void setEndLon(Double endLon) {
		this.endLon = endLon;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

}

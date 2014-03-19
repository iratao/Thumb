package com.app.doublecheck;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.doublecheck.data.Record;
import com.app.doublecheck.network.ServerService;
import android.location.Address;

public class Connector {
	private ServerService server;
	
	public Connector(){
		server = new ServerService();
	}
	
	public Address getStartAddress(){return Global.startAddress;}
	public Address getEndAddress(){return Global.endAddress;}
	
	public void setStartAddress(Address addr){Global.startAddress = addr;}
	public void setEndAddress(Address addr){Global.endAddress = addr;}
	
	public String getStartAddressStr(){return Util.addressToStr(Global.startAddress);}
	public String getEndAddressStr(){return Util.addressToStr(Global.endAddress);}
	
	public void setStartDate(Date date){Global.startDate = date;}
	public void setEndDate(Date date){Global.endDate = date;}
	
	public String publishRecord(){
		return 
		server.addRecord(Global.userID, Global.userName, 
				Util.addressToHtmlStr(Global.startAddress), Util.addressToHtmlStr(Global.endAddress), 
				Global.startDate, Global.endDate, Global.startAddress.getLatitude(), Global.startAddress.getLongitude(), 
				Global.endAddress.getLatitude(), Global.endAddress.getLongitude(), 
				Global.otherInfo, Global.type, Global.category);
	}
	
	public List<com.app.doublecheck.data.Record> searchByStartPoint(Double startLat, Double startLon){
		//List<com.iratao.thumb.client.model.Record> serverRecords = new ArrayList<com.iratao.thumb.client.model.Record>();
		List<com.app.doublecheck.data.Record> records = new ArrayList<com.app.doublecheck.data.Record>();
		records = server.searchByStartPoint(Global.startAddress.getLatitude(), Global.startAddress.getLongitude(), Constants.RANGE);
		/**
		int i = 0;
		for(com.iratao.thumb.client.model.Record r : serverRecords){
			records.add(Util.serverRecordToClientRecord(r));
			i++;
			if(i == 5)
				break;
		}
		*/
		if(records.size()>=6){
			return records.subList(records.size() - 6, records.size() - 1);
		}else{
			return records;
		}
	}
	
	public List<com.app.doublecheck.data.Record> searchByEndPoint(Double endLat, Double endLon){
		//List<com.iratao.thumb.client.model.Record> serverRecords = new ArrayList<com.iratao.thumb.client.model.Record>();
		List<com.app.doublecheck.data.Record> records = new ArrayList<com.app.doublecheck.data.Record>();
		records = server.searchByEndPoint(Global.endAddress.getLatitude(), Global.endAddress.getLongitude(), Constants.RANGE);
		/**
		int i = 0;
		for(com.iratao.thumb.client.model.Record r : serverRecords){
			records.add(Util.serverRecordToClientRecord(r));
			i++;
			if(i == 5)
				break;
		}
		*/
		if(records.size()>=6){
			return records.subList(records.size() - 6, records.size() - 1);
		}else{
			return records;
		}
	}
	
	public List<com.app.doublecheck.data.Record> searchByStartEndPoint(Double startLat, Double startLon, Double endLat, Double endLon){
		//List<com.iratao.thumb.client.model.Record> serverRecords = new ArrayList<com.iratao.thumb.client.model.Record>();
		List<com.app.doublecheck.data.Record> records = new ArrayList<com.app.doublecheck.data.Record>();
		records = server.searchByStartEndPoint(Global.startAddress.getLatitude(), Global.startAddress.getLongitude(), 
				Global.endAddress.getLatitude(), Global.endAddress.getLongitude(), Constants.RANGE);
		/**
		int i = 0;
		for(com.iratao.thumb.client.model.Record r : serverRecords){
			records.add(Util.serverRecordToClientRecord(r));
			i++;
			if(i == 5)
				break;
		}
		*/
		if(records.size()>=6){
			return records.subList(records.size() - 6, records.size() - 1);
		}else{
			return records;
		}
	}
	
	List<Record> getLatestRecord(int start, int end){
		/**
		List<Record> records = null;
		records = Util.getSampleRecords(0, 5);
		return records;
		*/
		List<com.app.doublecheck.data.Record> records = new ArrayList<com.app.doublecheck.data.Record>();
		//List<com.iratao.thumb.client.model.Record> serverRecords = null;
		//records = server.getLatestRecord(new Date(2011-1900, 5-1, 23), new Date());
		records = server.getLatestRecord(
				new Date(new Date().getYear(), new Date().getMonth()-1 , new Date().getDate()), 
				new Date(new Date().getYear(), new Date().getMonth() + 1, new Date().getDate()));
		/**
		int i = 0;
		for(com.iratao.thumb.client.model.Record r : serverRecords){
			records.add(Util.serverRecordToClientRecord(r));
			i++;
			if(i == 5)
				break;
		}
		*/
		if(records.size()>=6){
			return records.subList(records.size() - 6, records.size() - 1);
		}else{
			return records;
		}
		
	}
	
	public String getUserPhoneNumber(int userID){
		// TODO
		String p = "";
		p = server.getUser(userID).phoneNumber;
		return p;
	}
	
	public String getUserEmail(int userID){
		// TODO
		String e = "";
		e = server.getUser(userID).email;
		return e;
	}
	

}

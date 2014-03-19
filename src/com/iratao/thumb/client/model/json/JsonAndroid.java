package com.iratao.thumb.client.model.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.app.doublecheck.Constants;
import com.app.doublecheck.Util;
import com.app.doublecheck.data.Record;
import com.app.doublecheck.data.User;
import com.app.doublecheck.network.ServerService;


public class JsonAndroid {
	public List<com.app.doublecheck.data.Record> fromRecordListJson(String content) {
		List<com.app.doublecheck.data.Record> records = new ArrayList<com.app.doublecheck.data.Record>();
		try {
			JSONArray jArray = new JSONArray(content);
			int length = jArray.length();
			for(int i = 0; i < length; i++){
				JSONObject obj = jArray.getJSONObject(i);
				int recordID = obj.getInt("recordID");
				int userID = obj.getInt("userID");
				String userName = obj.getString("userName");
				String startAddress = obj.getString("startAddress");
				String endAddress = obj.getString("endAddress");
				String startDate = obj.getString("startDate");
				String endDate = obj.getString("endDate");
				Double startLat = obj.getDouble("startLat");
				Double startLon = obj.getDouble("startLon");
				Double endLat = obj.getDouble("endLat");
				Double endLon = obj.getDouble("endLon");
				String other = obj.getString("other");
				int type = obj.getInt("type");
				int category = obj.getInt("category");
				com.app.doublecheck.data.Record record = new Record(recordID, userID, userName, startAddress, endAddress, 
						Util.strToDateJson(startDate), Util.strToDateJson(endDate), startLat, startLon, endLat, endLon, other, type, category);
			    records.add(record);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public Record fromRecordJson(String content) {
		
		return null;
	}
	
	public User fromUserJson(String content) {
		User user = null;
		try {
			JSONObject jObject =  new JSONObject(content);
			int userID = jObject.getInt("userID");
			String userName = jObject.getString("userName");
			String phoneNumber = jObject.getString("phoneNumber");
			String email = jObject.getString("email");
			user = new User(userID, userName, phoneNumber, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
}

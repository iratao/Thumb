package com.app.doublecheck.network;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.app.doublecheck.Constants;
import com.app.doublecheck.Util;
import com.app.doublecheck.data.User;
import com.iratao.thumb.client.model.json.JsonAndroid;

public class ServerService {
	
	private static final String CLASSTAG = ServerService.class.getSimpleName();
	
	private static final String HOST = "http://10.0.2.2:8989";
	
	public ServerService(){
		
	}
	
	public String addRecord(Integer userID, String userName, String startAddress, String endAddress, 
			Date startDate, Date endDate, Double startLat, Double startLon, Double endLat, Double endLon, String other, Integer type, Integer category){
		String addr = HOST + "/addRecord?userID=" + userID + "&userName=" + userName + "&startAddress=" + startAddress + 
		"&endAddress=" + endAddress + "&startDate=" + Util.dateToStr(startDate) + "&endDate=" + Util.dateToStr(endDate) + 
		"&startLat=" + startLat + "&startLon=" + startLon + "&endLat=" + endLat + "&endLon=" + endLon + 
		"&other=" + other + "&type=" + type + "&category=" + category;
		System.out.println(addr);
		
		return getHttpResponse(addr);
	}
	
	public List<com.app.doublecheck.data.Record> searchByStartPoint(Double startLat, Double startLon, Double range){
		String addr = HOST + "/searchByStartPoint?startLat=" + startLat + "&startLon=" + startLon + "&range=" + range;
		String response = getHttpResponse(addr);
		JsonAndroid json = new JsonAndroid();
		return json.fromRecordListJson(response);
	}
	
    public List<com.app.doublecheck.data.Record> searchByEndPoint(Double endLat, Double endLon, Double range){
    	String addr = HOST + "/searchByEndPoint?endLat=" + endLat + "&endLon=" + endLon + "&range=" + range;
		String response = getHttpResponse(addr);
		JsonAndroid json = new JsonAndroid();
    	return json.fromRecordListJson(response);
    }
    
    public List<com.app.doublecheck.data.Record> searchByStartEndPoint(Double startLat, Double startLon, Double endLat, Double endLon, Double range){
    	String addr = HOST + "/searchByStartEndPoint?startLat=" + startLat + "&startLon=" + startLon + 
    	"&endLat=" + endLat + "&endLon=" + endLon + "&range=" + range;
    	String response = getHttpResponse(addr);
    	JsonAndroid json = new JsonAndroid();
    	return json.fromRecordListJson(response);
    }
    
    public List<com.app.doublecheck.data.Record> getLatestRecord(Date start, Date end){
    	String addr = HOST + "/getLatestRecord?start="+ Util.dateToStr(start) + "&end=" + Util.dateToStr(end);
    	String response = getHttpResponse(addr);
    	//System.out.println("GetLatestRecords: " + response);
    	JsonAndroid json = new JsonAndroid();
    	return json.fromRecordListJson(response);
    }
    
    public com.app.doublecheck.data.User getUser(Integer userID){
    	String addr = HOST + "/getUser?userID=" + userID;
    	String response = getHttpResponse(addr);
    	JsonAndroid json = new JsonAndroid();
    	return json.fromUserJson(response);
    }
    
    private static String getHttpResponse(String location) {
        String result = "";
        URL url = null;
        Log.d(Constants.LOGTAG, " " + ServerService.CLASSTAG + " location = " + location);

        try {
            url = new URL(location);
            Log.d(Constants.LOGTAG, " " + ServerService.CLASSTAG + " url = " + url);
        } catch (MalformedURLException e) {
            Log.e(Constants.LOGTAG, " " + ServerService.CLASSTAG + " " + e.getMessage());
        }

        if (url != null) {
            try {
            	Log.d(Constants.LOGTAG, " " + ServerService.CLASSTAG + " enter try{}");
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                Log.d(Constants.LOGTAG, " " + ServerService.CLASSTAG + " connection opened");
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String inputLine;
                Log.d(Constants.LOGTAG, " " + ServerService.CLASSTAG + " after BufferedReader");
                int lineCount = 0; // limit the lines for the example
                //in.readLine();
                while ((inputLine = in.readLine()) != null) {
                	//Log.d(Constants.LOGTAG, " " + ServerService.CLASSTAG + " in while");
                    lineCount++;
                    Log.v(Constants.LOGTAG, " " + ServerService.CLASSTAG + " inputLine = " + inputLine);
                    result += "\n" + inputLine;
                }
                in.close();
                urlConn.disconnect();

            } catch (IOException e) {
                Log.e(Constants.LOGTAG, " " + ServerService.CLASSTAG + " " + e.getMessage());
            }
        } else {
            Log.e(Constants.LOGTAG, " " + ServerService.CLASSTAG + " url NULL");
        }
        //System.out.println(result);
        return result;
    }
    
    

}
